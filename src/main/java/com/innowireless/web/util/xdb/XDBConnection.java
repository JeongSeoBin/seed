package com.innowireless.web.util.xdb;

import com.innowireless.web.api.ApiException;
import com.innowireless.web.api.ErrorCodes;
import com.innowireless.xdb5gcp.rui.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * XDB Connection 연동 및 메서드 제공
 */
@Slf4j
public class XDBConnection {

    private Integer xdbId;
    private String xdbHost;
    private Integer xdbPort;

    private ManagedChannel channel;
    private XdbServiceGrpc.XdbServiceBlockingStub blockingStub;
    private XdbServiceGrpc.XdbServiceStub stub;

    public static class XdbConnectionInfo {
        Integer id;
        String host;
        Integer port;
    }

    public XDBConnection(XdbConnectionInfo connectInfo) {
        this.xdbId = connectInfo.id;
        this.xdbHost = connectInfo.host;
        this.xdbPort = connectInfo.port;

        initConnection();
    }

    public String getHost() {
        return xdbHost;
    }

    public Integer getPort() {
        return xdbPort;
    }

    private void initConnection() {
        log.info("Initializing XdbConnection: id = {}, host = {}, port = {}", xdbId, xdbHost, xdbPort);

        channel = ManagedChannelBuilder.forAddress(xdbHost, xdbPort).usePlaintext().build();
        blockingStub = XdbServiceGrpc.newBlockingStub(channel);
        stub = XdbServiceGrpc.newStub(channel);

        log.info("Initialized XdbConnection: id = {}, host = {}, port = {}", xdbId, xdbHost, xdbPort);
    }

    public synchronized void destroy() {
        log.info("Uninitializing XdbConnection: id = {}, host = {}, port = {}", xdbId, xdbHost, xdbPort);
        shutdownConnection(false);
        log.info("Uninitialized XdbConnection: id = {}, host = {}, port = {}", xdbId, xdbHost, xdbPort);
    }

    private void shutdownConnection(boolean isNow) {
        ManagedChannel thisChannel = channel;

        if ((thisChannel != null) && (!thisChannel.isShutdown())) {
            try {
                log.info("Shutting down RPC channel");

                if (isNow) {
                    thisChannel.shutdownNow();

                    if (!thisChannel.awaitTermination(1, TimeUnit.SECONDS))
                        log.info("RPC channel still not shutdown; returning anyway");
                } else {
                    thisChannel.shutdown();

                    if (!thisChannel.awaitTermination(5, TimeUnit.SECONDS)) {
                        log.info("RPC channel still not shutdown; now shutting down forcefully");
                        thisChannel.shutdownNow();

                        if (!thisChannel.awaitTermination(1, TimeUnit.SECONDS))
                            log.info("RPC channel still not shutdown; returning anyway");
                    }
                }
            } catch (InterruptedException e) {
                log.error("Failed to shutdown RPC channel", e);
            }
        }
    }

    // proto API
    // checkBaseResult()로 BaseResult의 ResultCode를 검사해서 RESULT_OK가 아니면 throw 한다.

    public GenerateResult generateQueryKey(String userIdentity, String sqlString,
                                           long startTimestamp, long endTimestamp)  // timestamp는 microsecond 단위 unix epoch time
    {
        QueryRequest request = QueryRequest.newBuilder()
            .setUserIdentity(userIdentity)
            .setSqlString(sqlString)
            .setStartTimestamp(startTimestamp)
            .setEndTimestamp(endTimestamp)
            .build();
        GenerateResult result = blockingStub.generateQueryKey(request);
        checkBaseResult(result.getBaseResult());
        return result;
    }

    public interface ExecuteQueryCallback {
        /**
         * rpc의 onError, onCompleted를 통합한 것.
         * executeQueryKey()를 call한 thread와는 다른 thread에서 call 된다.
         * 극단적인 경우는 executeQueryKey()가 return하기 전에 call 될 수도 있다.
         *
         * @param result    - 마지막 result. onCompleted로 끝났을 때 null이 아니다.
         * @param throwable - onError로 끝났을 때 null이 아니다.
         */
        void onCompleted(@Nullable ExecutionResult result, @Nullable Throwable throwable);
    }

    // server로부터 첫 응답이 왔을 때 해당 응답을 return 한다.
    // caller가 실제 끝났는지의 여부를 알려면 cb나 getExecutionResult를 사용해야 한다.
    public ExecutionResult executeQueryKey(String key, @Nullable ExecuteQueryCallback cb)
        throws Throwable {
        final CountDownLatch finishInitialWaitLatch = new CountDownLatch(1);
        final ExecuteInfoReceiver infoReceiver = new ExecuteInfoReceiver();
        Key request = Key.newBuilder()
            .setKeyString(key)
            .build();
        stub.executeQueryKey(request, new StreamObserver<ExecutionResult>() {
            @Override
            public void onNext(ExecutionResult executionResult) {
                log.debug("{}: progress: {}", key, executionResult.toString().trim());
                infoReceiver.setResult(executionResult);
                finishInitialWait();  // 정상적으로 시작.
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("{}: execute error: {}", key, throwable);

                infoReceiver.setError(throwable);
                // finishLatch.await()이 return하기 전에만 쓸모 있다.
                // argument error 등, query 작업이 시작되기 전에 발생하는 error 처리용이다.
                finishInitialWait();
                if (cb != null)
                    cb.onCompleted(null, throwable);
            }

            @Override
            public void onCompleted() {
                log.debug("{}: execute complete", key);
                finishInitialWait();  // onNext() 없이 정상적으로 끝나는 경우는 없지만, 일단 해 둔다.
                if (cb != null)
                    cb.onCompleted(infoReceiver.lastResult, null);
            }

            private void finishInitialWait() {
                if (!finishedInitialWait) {
                    finishedInitialWait = true;
                    finishInitialWaitLatch.countDown();
                }
            }

            private boolean finishedInitialWait = false;
        });
        finishInitialWaitLatch.await();
        if (infoReceiver.error != null)
            throw infoReceiver.error;
        return infoReceiver.firstResult;
    }

    private static class ExecuteInfoReceiver {
        public volatile ExecutionResult firstResult;
        public volatile ExecutionResult lastResult;
        public volatile Throwable error;

        public void setResult(ExecutionResult result) {
            if (this.firstResult == null)
                this.firstResult = result;
            this.lastResult = result;
        }

        public void setError(Throwable error) {
            this.error = error;
        }
    }

    public ExecutionResult getExecutionInfo(String key) {
        Key request = Key.newBuilder()
            .setKeyString(key)
            .build();
        ExecutionResult result = blockingStub.getExecutionInfo(request);
        checkBaseResult(result.getBaseResult());
        return result;
    }

    public void removeQueryKey(String key) {
        Key request = Key.newBuilder()
            .setKeyString(key)
            .build();
        BaseResult result = blockingStub.removeQueryKey(request);
        checkBaseResult(result);
    }

    public void removeQueryKeyAll() {
        Empty request = Empty.newBuilder().build();
        BaseResult result = blockingStub.removeQueryKeyAll(request);
        checkBaseResult(result);
    }

    public Iterator<Dataset> getDataset(String tableName, long recordOffset, long recordCount) {
        DatasetRequest request = DatasetRequest.newBuilder()
            .setTableName(tableName)
            .setRecordOffset(recordOffset)
            .setRecordCount(recordCount)
            .build();
        return blockingStub.getDataset(request);
    }

    public TableInfoListResult getTableInfoList(
        TableInfoListRequest.RequestTableType tableType,
        String tableNameRegex) throws ApiException {
        TableInfoListRequest request = TableInfoListRequest.newBuilder()
            .setTableType(tableType)
            .setTableNameRegex(tableNameRegex)
            .build();
        TableInfoListResult result = blockingStub.getTableInfoList(request);
        checkBaseResult(result.getBaseResult());
        return result;
    }

    public TableSchemaResult getTableSchema(String tableName) {
        TableName request = TableName.newBuilder().setTableName(tableName).build();
        TableSchemaResult result = blockingStub.getTableSchema(request);
        checkBaseResult(result.getBaseResult());
        return result;
    }

    // startDatetime, endDatetime은 'YYYY-MM-DD' format
    public void removeTimePartition(String tableName, String startDatetime, String endDatetime) {
        RemoveTimePartitionRequest request = RemoveTimePartitionRequest.newBuilder()
            .setTableName(tableName)
            .setStartDatetime(startDatetime)
            .setEndDatetime(endDatetime)
            .build();
        BaseResult result = blockingStub.removeTimePartition(request);
        checkBaseResult(result);
    }

    private void checkBaseResult(BaseResult result) throws ApiException {
        if (result.getResultCode() != BaseResult.ResultCode.RESULT_OK) {
            if ((result.getResultCode() == BaseResult.ResultCode.RESULT_INPUT_SYNTAX_ERROR) &&
                (result.getSyntaxErrorPos().getErrorPosExist())) {
                log.error("BaseResult NOT OK: resultCode = {}, errorMessage = {}, " +
                        "syntaxErrorPos: startLine = {}, endLine = {}, startPos = {}, endPos = {}",
                    result.getResultCodeValue(), result.getErrorMessage(),
                    result.getSyntaxErrorPos().getStartLine(),
                    result.getSyntaxErrorPos().getEndLine(),
                    result.getSyntaxErrorPos().getStartPos(),
                    result.getSyntaxErrorPos().getEndPos());
                throw new XdbBaseException(
                    ErrorCodes.XDB_RESULT + result.getResultCodeValue(),
                    result.getErrorMessage(),
                    result.getSyntaxErrorPos().getStartLine(),
                    result.getSyntaxErrorPos().getEndLine(),
                    result.getSyntaxErrorPos().getStartPos(),
                    result.getSyntaxErrorPos().getEndPos());
            } else {
                log.error("BaseResult NOT OK: resultCode = {}, errorMessage = {}",
                    result.getResultCodeValue(), result.getErrorMessage());
                throw new ApiException(
                    ErrorCodes.XDB_RESULT + result.getResultCodeValue(),
                    result.getErrorMessage());
            }
        }
    }

    public static class XdbBaseException extends ApiException {
        public XdbBaseException(int code, String message, int startLine, int endLine,
                                int startPos, int endPos) {
            super(code, message);
            this.startLine = startLine;
            this.endLine = endLine;
            this.startPos = startPos;
            this.endPos = endPos;
        }

        public int startLine;
        public int endLine;
        public int startPos;
        public int endPos;
    }
}
