package com.innowireless.xdb5gcp.rui;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.18.0)",
    comments = "Source: xdb_5gcp_rui.proto")
public final class XdbServiceGrpc {

  private XdbServiceGrpc() {}

  public static final String SERVICE_NAME = "xdb_5gcp.rui.XdbService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.QueryRequest,
      com.innowireless.xdb5gcp.rui.GenerateResult> getGenerateQueryKeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GenerateQueryKey",
      requestType = com.innowireless.xdb5gcp.rui.QueryRequest.class,
      responseType = com.innowireless.xdb5gcp.rui.GenerateResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.QueryRequest,
      com.innowireless.xdb5gcp.rui.GenerateResult> getGenerateQueryKeyMethod() {
    io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.QueryRequest, com.innowireless.xdb5gcp.rui.GenerateResult> getGenerateQueryKeyMethod;
    if ((getGenerateQueryKeyMethod = XdbServiceGrpc.getGenerateQueryKeyMethod) == null) {
      synchronized (XdbServiceGrpc.class) {
        if ((getGenerateQueryKeyMethod = XdbServiceGrpc.getGenerateQueryKeyMethod) == null) {
          XdbServiceGrpc.getGenerateQueryKeyMethod = getGenerateQueryKeyMethod = 
              io.grpc.MethodDescriptor.<com.innowireless.xdb5gcp.rui.QueryRequest, com.innowireless.xdb5gcp.rui.GenerateResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "xdb_5gcp.rui.XdbService", "GenerateQueryKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.innowireless.xdb5gcp.rui.QueryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.innowireless.xdb5gcp.rui.GenerateResult.getDefaultInstance()))
                  .setSchemaDescriptor(new XdbServiceMethodDescriptorSupplier("GenerateQueryKey"))
                  .build();
          }
        }
     }
     return getGenerateQueryKeyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.Key,
      com.innowireless.xdb5gcp.rui.ExecutionResult> getExecuteQueryKeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExecuteQueryKey",
      requestType = com.innowireless.xdb5gcp.rui.Key.class,
      responseType = com.innowireless.xdb5gcp.rui.ExecutionResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.Key,
      com.innowireless.xdb5gcp.rui.ExecutionResult> getExecuteQueryKeyMethod() {
    io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.Key, com.innowireless.xdb5gcp.rui.ExecutionResult> getExecuteQueryKeyMethod;
    if ((getExecuteQueryKeyMethod = XdbServiceGrpc.getExecuteQueryKeyMethod) == null) {
      synchronized (XdbServiceGrpc.class) {
        if ((getExecuteQueryKeyMethod = XdbServiceGrpc.getExecuteQueryKeyMethod) == null) {
          XdbServiceGrpc.getExecuteQueryKeyMethod = getExecuteQueryKeyMethod = 
              io.grpc.MethodDescriptor.<com.innowireless.xdb5gcp.rui.Key, com.innowireless.xdb5gcp.rui.ExecutionResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "xdb_5gcp.rui.XdbService", "ExecuteQueryKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.innowireless.xdb5gcp.rui.Key.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.innowireless.xdb5gcp.rui.ExecutionResult.getDefaultInstance()))
                  .setSchemaDescriptor(new XdbServiceMethodDescriptorSupplier("ExecuteQueryKey"))
                  .build();
          }
        }
     }
     return getExecuteQueryKeyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.Key,
      com.innowireless.xdb5gcp.rui.ExecutionResult> getGetExecutionInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetExecutionInfo",
      requestType = com.innowireless.xdb5gcp.rui.Key.class,
      responseType = com.innowireless.xdb5gcp.rui.ExecutionResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.Key,
      com.innowireless.xdb5gcp.rui.ExecutionResult> getGetExecutionInfoMethod() {
    io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.Key, com.innowireless.xdb5gcp.rui.ExecutionResult> getGetExecutionInfoMethod;
    if ((getGetExecutionInfoMethod = XdbServiceGrpc.getGetExecutionInfoMethod) == null) {
      synchronized (XdbServiceGrpc.class) {
        if ((getGetExecutionInfoMethod = XdbServiceGrpc.getGetExecutionInfoMethod) == null) {
          XdbServiceGrpc.getGetExecutionInfoMethod = getGetExecutionInfoMethod = 
              io.grpc.MethodDescriptor.<com.innowireless.xdb5gcp.rui.Key, com.innowireless.xdb5gcp.rui.ExecutionResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "xdb_5gcp.rui.XdbService", "GetExecutionInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.innowireless.xdb5gcp.rui.Key.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.innowireless.xdb5gcp.rui.ExecutionResult.getDefaultInstance()))
                  .setSchemaDescriptor(new XdbServiceMethodDescriptorSupplier("GetExecutionInfo"))
                  .build();
          }
        }
     }
     return getGetExecutionInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.Key,
      com.innowireless.xdb5gcp.rui.BaseResult> getRemoveQueryKeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveQueryKey",
      requestType = com.innowireless.xdb5gcp.rui.Key.class,
      responseType = com.innowireless.xdb5gcp.rui.BaseResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.Key,
      com.innowireless.xdb5gcp.rui.BaseResult> getRemoveQueryKeyMethod() {
    io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.Key, com.innowireless.xdb5gcp.rui.BaseResult> getRemoveQueryKeyMethod;
    if ((getRemoveQueryKeyMethod = XdbServiceGrpc.getRemoveQueryKeyMethod) == null) {
      synchronized (XdbServiceGrpc.class) {
        if ((getRemoveQueryKeyMethod = XdbServiceGrpc.getRemoveQueryKeyMethod) == null) {
          XdbServiceGrpc.getRemoveQueryKeyMethod = getRemoveQueryKeyMethod = 
              io.grpc.MethodDescriptor.<com.innowireless.xdb5gcp.rui.Key, com.innowireless.xdb5gcp.rui.BaseResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "xdb_5gcp.rui.XdbService", "RemoveQueryKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.innowireless.xdb5gcp.rui.Key.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.innowireless.xdb5gcp.rui.BaseResult.getDefaultInstance()))
                  .setSchemaDescriptor(new XdbServiceMethodDescriptorSupplier("RemoveQueryKey"))
                  .build();
          }
        }
     }
     return getRemoveQueryKeyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.Empty,
      com.innowireless.xdb5gcp.rui.BaseResult> getRemoveQueryKeyAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveQueryKeyAll",
      requestType = com.innowireless.xdb5gcp.rui.Empty.class,
      responseType = com.innowireless.xdb5gcp.rui.BaseResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.Empty,
      com.innowireless.xdb5gcp.rui.BaseResult> getRemoveQueryKeyAllMethod() {
    io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.Empty, com.innowireless.xdb5gcp.rui.BaseResult> getRemoveQueryKeyAllMethod;
    if ((getRemoveQueryKeyAllMethod = XdbServiceGrpc.getRemoveQueryKeyAllMethod) == null) {
      synchronized (XdbServiceGrpc.class) {
        if ((getRemoveQueryKeyAllMethod = XdbServiceGrpc.getRemoveQueryKeyAllMethod) == null) {
          XdbServiceGrpc.getRemoveQueryKeyAllMethod = getRemoveQueryKeyAllMethod = 
              io.grpc.MethodDescriptor.<com.innowireless.xdb5gcp.rui.Empty, com.innowireless.xdb5gcp.rui.BaseResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "xdb_5gcp.rui.XdbService", "RemoveQueryKeyAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.innowireless.xdb5gcp.rui.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.innowireless.xdb5gcp.rui.BaseResult.getDefaultInstance()))
                  .setSchemaDescriptor(new XdbServiceMethodDescriptorSupplier("RemoveQueryKeyAll"))
                  .build();
          }
        }
     }
     return getRemoveQueryKeyAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.DatasetRequest,
      com.innowireless.xdb5gcp.rui.Dataset> getGetDatasetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDataset",
      requestType = com.innowireless.xdb5gcp.rui.DatasetRequest.class,
      responseType = com.innowireless.xdb5gcp.rui.Dataset.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.DatasetRequest,
      com.innowireless.xdb5gcp.rui.Dataset> getGetDatasetMethod() {
    io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.DatasetRequest, com.innowireless.xdb5gcp.rui.Dataset> getGetDatasetMethod;
    if ((getGetDatasetMethod = XdbServiceGrpc.getGetDatasetMethod) == null) {
      synchronized (XdbServiceGrpc.class) {
        if ((getGetDatasetMethod = XdbServiceGrpc.getGetDatasetMethod) == null) {
          XdbServiceGrpc.getGetDatasetMethod = getGetDatasetMethod = 
              io.grpc.MethodDescriptor.<com.innowireless.xdb5gcp.rui.DatasetRequest, com.innowireless.xdb5gcp.rui.Dataset>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "xdb_5gcp.rui.XdbService", "GetDataset"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.innowireless.xdb5gcp.rui.DatasetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.innowireless.xdb5gcp.rui.Dataset.getDefaultInstance()))
                  .setSchemaDescriptor(new XdbServiceMethodDescriptorSupplier("GetDataset"))
                  .build();
          }
        }
     }
     return getGetDatasetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.TableInfoListRequest,
      com.innowireless.xdb5gcp.rui.TableInfoListResult> getGetTableInfoListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTableInfoList",
      requestType = com.innowireless.xdb5gcp.rui.TableInfoListRequest.class,
      responseType = com.innowireless.xdb5gcp.rui.TableInfoListResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.TableInfoListRequest,
      com.innowireless.xdb5gcp.rui.TableInfoListResult> getGetTableInfoListMethod() {
    io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.TableInfoListRequest, com.innowireless.xdb5gcp.rui.TableInfoListResult> getGetTableInfoListMethod;
    if ((getGetTableInfoListMethod = XdbServiceGrpc.getGetTableInfoListMethod) == null) {
      synchronized (XdbServiceGrpc.class) {
        if ((getGetTableInfoListMethod = XdbServiceGrpc.getGetTableInfoListMethod) == null) {
          XdbServiceGrpc.getGetTableInfoListMethod = getGetTableInfoListMethod = 
              io.grpc.MethodDescriptor.<com.innowireless.xdb5gcp.rui.TableInfoListRequest, com.innowireless.xdb5gcp.rui.TableInfoListResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "xdb_5gcp.rui.XdbService", "GetTableInfoList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.innowireless.xdb5gcp.rui.TableInfoListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.innowireless.xdb5gcp.rui.TableInfoListResult.getDefaultInstance()))
                  .setSchemaDescriptor(new XdbServiceMethodDescriptorSupplier("GetTableInfoList"))
                  .build();
          }
        }
     }
     return getGetTableInfoListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.TableName,
      com.innowireless.xdb5gcp.rui.TableSchemaResult> getGetTableSchemaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTableSchema",
      requestType = com.innowireless.xdb5gcp.rui.TableName.class,
      responseType = com.innowireless.xdb5gcp.rui.TableSchemaResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.TableName,
      com.innowireless.xdb5gcp.rui.TableSchemaResult> getGetTableSchemaMethod() {
    io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.TableName, com.innowireless.xdb5gcp.rui.TableSchemaResult> getGetTableSchemaMethod;
    if ((getGetTableSchemaMethod = XdbServiceGrpc.getGetTableSchemaMethod) == null) {
      synchronized (XdbServiceGrpc.class) {
        if ((getGetTableSchemaMethod = XdbServiceGrpc.getGetTableSchemaMethod) == null) {
          XdbServiceGrpc.getGetTableSchemaMethod = getGetTableSchemaMethod = 
              io.grpc.MethodDescriptor.<com.innowireless.xdb5gcp.rui.TableName, com.innowireless.xdb5gcp.rui.TableSchemaResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "xdb_5gcp.rui.XdbService", "GetTableSchema"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.innowireless.xdb5gcp.rui.TableName.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.innowireless.xdb5gcp.rui.TableSchemaResult.getDefaultInstance()))
                  .setSchemaDescriptor(new XdbServiceMethodDescriptorSupplier("GetTableSchema"))
                  .build();
          }
        }
     }
     return getGetTableSchemaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest,
      com.innowireless.xdb5gcp.rui.BaseResult> getRemoveTimePartitionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveTimePartition",
      requestType = com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest.class,
      responseType = com.innowireless.xdb5gcp.rui.BaseResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest,
      com.innowireless.xdb5gcp.rui.BaseResult> getRemoveTimePartitionMethod() {
    io.grpc.MethodDescriptor<com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest, com.innowireless.xdb5gcp.rui.BaseResult> getRemoveTimePartitionMethod;
    if ((getRemoveTimePartitionMethod = XdbServiceGrpc.getRemoveTimePartitionMethod) == null) {
      synchronized (XdbServiceGrpc.class) {
        if ((getRemoveTimePartitionMethod = XdbServiceGrpc.getRemoveTimePartitionMethod) == null) {
          XdbServiceGrpc.getRemoveTimePartitionMethod = getRemoveTimePartitionMethod = 
              io.grpc.MethodDescriptor.<com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest, com.innowireless.xdb5gcp.rui.BaseResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "xdb_5gcp.rui.XdbService", "RemoveTimePartition"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.innowireless.xdb5gcp.rui.BaseResult.getDefaultInstance()))
                  .setSchemaDescriptor(new XdbServiceMethodDescriptorSupplier("RemoveTimePartition"))
                  .build();
          }
        }
     }
     return getRemoveTimePartitionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static XdbServiceStub newStub(io.grpc.Channel channel) {
    return new XdbServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static XdbServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new XdbServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static XdbServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new XdbServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class XdbServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * generate a new query key using sql string
     * </pre>
     */
    public void generateQueryKey(com.innowireless.xdb5gcp.rui.QueryRequest request,
        io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.GenerateResult> responseObserver) {
      asyncUnimplementedUnaryCall(getGenerateQueryKeyMethod(), responseObserver);
    }

    /**
     * <pre>
     * execute a generated query(key)
     * return the stream of execution status when the status of a query finished
     * the maximum number of finished queries (== STATUS_DONE || == STATUS_ERROR) that can be stored is 1000
     * if it exceeds, the oldest finished query will be removed first 
     * **the result of SELECT statement (memory table) != query key object
     * **memory table has same name as query key
     * </pre>
     */
    public void executeQueryKey(com.innowireless.xdb5gcp.rui.Key request,
        io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.ExecutionResult> responseObserver) {
      asyncUnimplementedUnaryCall(getExecuteQueryKeyMethod(), responseObserver);
    }

    /**
     * <pre>
     * get the current execution status of a target query
     * </pre>
     */
    public void getExecutionInfo(com.innowireless.xdb5gcp.rui.Key request,
        io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.ExecutionResult> responseObserver) {
      asyncUnimplementedUnaryCall(getGetExecutionInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * remove query key manually
     * **memory table can't be deleted by this RPC
     * </pre>
     */
    public void removeQueryKey(com.innowireless.xdb5gcp.rui.Key request,
        io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.BaseResult> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveQueryKeyMethod(), responseObserver);
    }

    /**
     */
    public void removeQueryKeyAll(com.innowireless.xdb5gcp.rui.Empty request,
        io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.BaseResult> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveQueryKeyAllMethod(), responseObserver);
    }

    /**
     * <pre>
     * get records using table name (USER_TABLE and MEMORY_TABLE)
     * </pre>
     */
    public void getDataset(com.innowireless.xdb5gcp.rui.DatasetRequest request,
        io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.Dataset> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDatasetMethod(), responseObserver);
    }

    /**
     * <pre>
     * get table info list with regex
     * </pre>
     */
    public void getTableInfoList(com.innowireless.xdb5gcp.rui.TableInfoListRequest request,
        io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.TableInfoListResult> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTableInfoListMethod(), responseObserver);
    }

    /**
     * <pre>
     * get detailed table info with table name
     * </pre>
     */
    public void getTableSchema(com.innowireless.xdb5gcp.rui.TableName request,
        io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.TableSchemaResult> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTableSchemaMethod(), responseObserver);
    }

    /**
     * <pre>
     * remove table by using datetime (U)SER_TABLE and MEMORY_TABLE)
     * </pre>
     */
    public void removeTimePartition(com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest request,
        io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.BaseResult> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveTimePartitionMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGenerateQueryKeyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.innowireless.xdb5gcp.rui.QueryRequest,
                com.innowireless.xdb5gcp.rui.GenerateResult>(
                  this, METHODID_GENERATE_QUERY_KEY)))
          .addMethod(
            getExecuteQueryKeyMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.innowireless.xdb5gcp.rui.Key,
                com.innowireless.xdb5gcp.rui.ExecutionResult>(
                  this, METHODID_EXECUTE_QUERY_KEY)))
          .addMethod(
            getGetExecutionInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.innowireless.xdb5gcp.rui.Key,
                com.innowireless.xdb5gcp.rui.ExecutionResult>(
                  this, METHODID_GET_EXECUTION_INFO)))
          .addMethod(
            getRemoveQueryKeyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.innowireless.xdb5gcp.rui.Key,
                com.innowireless.xdb5gcp.rui.BaseResult>(
                  this, METHODID_REMOVE_QUERY_KEY)))
          .addMethod(
            getRemoveQueryKeyAllMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.innowireless.xdb5gcp.rui.Empty,
                com.innowireless.xdb5gcp.rui.BaseResult>(
                  this, METHODID_REMOVE_QUERY_KEY_ALL)))
          .addMethod(
            getGetDatasetMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.innowireless.xdb5gcp.rui.DatasetRequest,
                com.innowireless.xdb5gcp.rui.Dataset>(
                  this, METHODID_GET_DATASET)))
          .addMethod(
            getGetTableInfoListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.innowireless.xdb5gcp.rui.TableInfoListRequest,
                com.innowireless.xdb5gcp.rui.TableInfoListResult>(
                  this, METHODID_GET_TABLE_INFO_LIST)))
          .addMethod(
            getGetTableSchemaMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.innowireless.xdb5gcp.rui.TableName,
                com.innowireless.xdb5gcp.rui.TableSchemaResult>(
                  this, METHODID_GET_TABLE_SCHEMA)))
          .addMethod(
            getRemoveTimePartitionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest,
                com.innowireless.xdb5gcp.rui.BaseResult>(
                  this, METHODID_REMOVE_TIME_PARTITION)))
          .build();
    }
  }

  /**
   */
  public static final class XdbServiceStub extends io.grpc.stub.AbstractStub<XdbServiceStub> {
    private XdbServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private XdbServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected XdbServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new XdbServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * generate a new query key using sql string
     * </pre>
     */
    public void generateQueryKey(com.innowireless.xdb5gcp.rui.QueryRequest request,
        io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.GenerateResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGenerateQueryKeyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * execute a generated query(key)
     * return the stream of execution status when the status of a query finished
     * the maximum number of finished queries (== STATUS_DONE || == STATUS_ERROR) that can be stored is 1000
     * if it exceeds, the oldest finished query will be removed first 
     * **the result of SELECT statement (memory table) != query key object
     * **memory table has same name as query key
     * </pre>
     */
    public void executeQueryKey(com.innowireless.xdb5gcp.rui.Key request,
        io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.ExecutionResult> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getExecuteQueryKeyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get the current execution status of a target query
     * </pre>
     */
    public void getExecutionInfo(com.innowireless.xdb5gcp.rui.Key request,
        io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.ExecutionResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetExecutionInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * remove query key manually
     * **memory table can't be deleted by this RPC
     * </pre>
     */
    public void removeQueryKey(com.innowireless.xdb5gcp.rui.Key request,
        io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.BaseResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveQueryKeyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeQueryKeyAll(com.innowireless.xdb5gcp.rui.Empty request,
        io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.BaseResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveQueryKeyAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get records using table name (USER_TABLE and MEMORY_TABLE)
     * </pre>
     */
    public void getDataset(com.innowireless.xdb5gcp.rui.DatasetRequest request,
        io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.Dataset> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetDatasetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get table info list with regex
     * </pre>
     */
    public void getTableInfoList(com.innowireless.xdb5gcp.rui.TableInfoListRequest request,
        io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.TableInfoListResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTableInfoListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get detailed table info with table name
     * </pre>
     */
    public void getTableSchema(com.innowireless.xdb5gcp.rui.TableName request,
        io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.TableSchemaResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTableSchemaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * remove table by using datetime (U)SER_TABLE and MEMORY_TABLE)
     * </pre>
     */
    public void removeTimePartition(com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest request,
        io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.BaseResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveTimePartitionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class XdbServiceBlockingStub extends io.grpc.stub.AbstractStub<XdbServiceBlockingStub> {
    private XdbServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private XdbServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected XdbServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new XdbServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * generate a new query key using sql string
     * </pre>
     */
    public com.innowireless.xdb5gcp.rui.GenerateResult generateQueryKey(com.innowireless.xdb5gcp.rui.QueryRequest request) {
      return blockingUnaryCall(
          getChannel(), getGenerateQueryKeyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * execute a generated query(key)
     * return the stream of execution status when the status of a query finished
     * the maximum number of finished queries (== STATUS_DONE || == STATUS_ERROR) that can be stored is 1000
     * if it exceeds, the oldest finished query will be removed first 
     * **the result of SELECT statement (memory table) != query key object
     * **memory table has same name as query key
     * </pre>
     */
    public java.util.Iterator<com.innowireless.xdb5gcp.rui.ExecutionResult> executeQueryKey(
        com.innowireless.xdb5gcp.rui.Key request) {
      return blockingServerStreamingCall(
          getChannel(), getExecuteQueryKeyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * get the current execution status of a target query
     * </pre>
     */
    public com.innowireless.xdb5gcp.rui.ExecutionResult getExecutionInfo(com.innowireless.xdb5gcp.rui.Key request) {
      return blockingUnaryCall(
          getChannel(), getGetExecutionInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * remove query key manually
     * **memory table can't be deleted by this RPC
     * </pre>
     */
    public com.innowireless.xdb5gcp.rui.BaseResult removeQueryKey(com.innowireless.xdb5gcp.rui.Key request) {
      return blockingUnaryCall(
          getChannel(), getRemoveQueryKeyMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.innowireless.xdb5gcp.rui.BaseResult removeQueryKeyAll(com.innowireless.xdb5gcp.rui.Empty request) {
      return blockingUnaryCall(
          getChannel(), getRemoveQueryKeyAllMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * get records using table name (USER_TABLE and MEMORY_TABLE)
     * </pre>
     */
    public java.util.Iterator<com.innowireless.xdb5gcp.rui.Dataset> getDataset(
        com.innowireless.xdb5gcp.rui.DatasetRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetDatasetMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * get table info list with regex
     * </pre>
     */
    public com.innowireless.xdb5gcp.rui.TableInfoListResult getTableInfoList(com.innowireless.xdb5gcp.rui.TableInfoListRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetTableInfoListMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * get detailed table info with table name
     * </pre>
     */
    public com.innowireless.xdb5gcp.rui.TableSchemaResult getTableSchema(com.innowireless.xdb5gcp.rui.TableName request) {
      return blockingUnaryCall(
          getChannel(), getGetTableSchemaMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * remove table by using datetime (U)SER_TABLE and MEMORY_TABLE)
     * </pre>
     */
    public com.innowireless.xdb5gcp.rui.BaseResult removeTimePartition(com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest request) {
      return blockingUnaryCall(
          getChannel(), getRemoveTimePartitionMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class XdbServiceFutureStub extends io.grpc.stub.AbstractStub<XdbServiceFutureStub> {
    private XdbServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private XdbServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected XdbServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new XdbServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * generate a new query key using sql string
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.innowireless.xdb5gcp.rui.GenerateResult> generateQueryKey(
        com.innowireless.xdb5gcp.rui.QueryRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGenerateQueryKeyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * get the current execution status of a target query
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.innowireless.xdb5gcp.rui.ExecutionResult> getExecutionInfo(
        com.innowireless.xdb5gcp.rui.Key request) {
      return futureUnaryCall(
          getChannel().newCall(getGetExecutionInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * remove query key manually
     * **memory table can't be deleted by this RPC
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.innowireless.xdb5gcp.rui.BaseResult> removeQueryKey(
        com.innowireless.xdb5gcp.rui.Key request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveQueryKeyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.innowireless.xdb5gcp.rui.BaseResult> removeQueryKeyAll(
        com.innowireless.xdb5gcp.rui.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveQueryKeyAllMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * get table info list with regex
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.innowireless.xdb5gcp.rui.TableInfoListResult> getTableInfoList(
        com.innowireless.xdb5gcp.rui.TableInfoListRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTableInfoListMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * get detailed table info with table name
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.innowireless.xdb5gcp.rui.TableSchemaResult> getTableSchema(
        com.innowireless.xdb5gcp.rui.TableName request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTableSchemaMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * remove table by using datetime (U)SER_TABLE and MEMORY_TABLE)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.innowireless.xdb5gcp.rui.BaseResult> removeTimePartition(
        com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveTimePartitionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GENERATE_QUERY_KEY = 0;
  private static final int METHODID_EXECUTE_QUERY_KEY = 1;
  private static final int METHODID_GET_EXECUTION_INFO = 2;
  private static final int METHODID_REMOVE_QUERY_KEY = 3;
  private static final int METHODID_REMOVE_QUERY_KEY_ALL = 4;
  private static final int METHODID_GET_DATASET = 5;
  private static final int METHODID_GET_TABLE_INFO_LIST = 6;
  private static final int METHODID_GET_TABLE_SCHEMA = 7;
  private static final int METHODID_REMOVE_TIME_PARTITION = 8;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final XdbServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(XdbServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GENERATE_QUERY_KEY:
          serviceImpl.generateQueryKey((com.innowireless.xdb5gcp.rui.QueryRequest) request,
              (io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.GenerateResult>) responseObserver);
          break;
        case METHODID_EXECUTE_QUERY_KEY:
          serviceImpl.executeQueryKey((com.innowireless.xdb5gcp.rui.Key) request,
              (io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.ExecutionResult>) responseObserver);
          break;
        case METHODID_GET_EXECUTION_INFO:
          serviceImpl.getExecutionInfo((com.innowireless.xdb5gcp.rui.Key) request,
              (io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.ExecutionResult>) responseObserver);
          break;
        case METHODID_REMOVE_QUERY_KEY:
          serviceImpl.removeQueryKey((com.innowireless.xdb5gcp.rui.Key) request,
              (io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.BaseResult>) responseObserver);
          break;
        case METHODID_REMOVE_QUERY_KEY_ALL:
          serviceImpl.removeQueryKeyAll((com.innowireless.xdb5gcp.rui.Empty) request,
              (io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.BaseResult>) responseObserver);
          break;
        case METHODID_GET_DATASET:
          serviceImpl.getDataset((com.innowireless.xdb5gcp.rui.DatasetRequest) request,
              (io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.Dataset>) responseObserver);
          break;
        case METHODID_GET_TABLE_INFO_LIST:
          serviceImpl.getTableInfoList((com.innowireless.xdb5gcp.rui.TableInfoListRequest) request,
              (io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.TableInfoListResult>) responseObserver);
          break;
        case METHODID_GET_TABLE_SCHEMA:
          serviceImpl.getTableSchema((com.innowireless.xdb5gcp.rui.TableName) request,
              (io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.TableSchemaResult>) responseObserver);
          break;
        case METHODID_REMOVE_TIME_PARTITION:
          serviceImpl.removeTimePartition((com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest) request,
              (io.grpc.stub.StreamObserver<com.innowireless.xdb5gcp.rui.BaseResult>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class XdbServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    XdbServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.innowireless.xdb5gcp.rui.RuiProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("XdbService");
    }
  }

  private static final class XdbServiceFileDescriptorSupplier
      extends XdbServiceBaseDescriptorSupplier {
    XdbServiceFileDescriptorSupplier() {}
  }

  private static final class XdbServiceMethodDescriptorSupplier
      extends XdbServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    XdbServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (XdbServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new XdbServiceFileDescriptorSupplier())
              .addMethod(getGenerateQueryKeyMethod())
              .addMethod(getExecuteQueryKeyMethod())
              .addMethod(getGetExecutionInfoMethod())
              .addMethod(getRemoveQueryKeyMethod())
              .addMethod(getRemoveQueryKeyAllMethod())
              .addMethod(getGetDatasetMethod())
              .addMethod(getGetTableInfoListMethod())
              .addMethod(getGetTableSchemaMethod())
              .addMethod(getRemoveTimePartitionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
