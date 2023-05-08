package com.innowireless.web.util.xdb;

import com.innowireless.web.util.MybatisMapperUtil;
import com.innowireless.xdb5gcp.rui.Dataset;
import com.innowireless.xdb5gcp.rui.ExecutionResult;
import com.innowireless.xdb5gcp.rui.GenerateResult;
import com.innowireless.xdb5gcp.rui.TableSchemaResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.CaseUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CountDownLatch;

@Component
// @ConditionalOnBean({XDBProperties.class, XDBConnectionManagementService.class})
@ConditionalOnBean(XDBProperties.class)
@Slf4j
@RequiredArgsConstructor
public class XDBClient {

    private final XDBProperties properties;
    private final XDBConnectionManagementService connectionManagementService;
    private final MybatisMapperUtil mapperUtil;

    @RequiredArgsConstructor
    private static class XDBExecuteQueryCallback implements XDBConnection.ExecuteQueryCallback {
        public volatile ExecutionResult result;
        public volatile Throwable throwable;

        private final CountDownLatch countDownLatch;

        @Override
        public void onCompleted(ExecutionResult result, Throwable throwable) {
            this.result = result;
            this.throwable = throwable;
            this.countDownLatch.countDown();
        }
    }

    /**
     * TODO
     *  기존 RDBMS & Mybatis 프로세스와 같이 쿼리할 수 있도록 한다.
     *  1. Generate > Execute > return [key]
     *     - 클라이언트에서 선처리를 위함 (ex. 무한스크롤)
     *  2. GetDataset with [key] and offset/limit(count)
     *  3. Generate > Execute > GetDataset with callback
     *  4. 3번 with [key]
     *  참고) Generate되는 부분에서는 startTime과 endTime이 필수로 필요하다.
     *  (값이 있을 경우 ISA Hub, -1일 경우 사용자 테이블에서 조회하는 것으로 전달 받음)
     */

    /**
     * 일반적으로 RDBMS를 사용하는 것과 동일한 방식으로 동작한다.
     *
     * @param queryId
     * @param sqlParam
     * @param startTime
     * @param endTime
     * @return
     * @throws Throwable
     */
    // TODO: startTime, endTime이 없는 경우도 생각해야 하는가?
    public List<Map<String, String>> select(final String queryId, final Object sqlParam,
                       final Long startTime, final Long endTime) throws Throwable {
        return this.select(XDBProperties.SINGLE_KEY, queryId, sqlParam, startTime, endTime);
    }

    public List<Map<String, String>> select(final Integer id, final String queryId, final Object sqlParam,
                       final Long startTime, final Long endTime) throws Throwable {
        return this.executeAwait(id, queryId, sqlParam, startTime, endTime);
    }

    /**
     * 쿼리를 실행하고 생성된 키를 반환한다.
     *
     * @param id
     * @param queryId
     * @param sqlParam
     * @param startTime
     * @param endTime
     * @return 생성된 키
     * @throws Throwable
     */
    private String execute(final Integer id, final String queryId, final Object sqlParam,
                           final Long startTime, final Long endTime) throws Throwable {

        final XDBConnection connection = connectionManagementService.get(id);

        log.debug("Start execute.");

        final String key = this.generateKey(connection, queryId, sqlParam, startTime, endTime);
        connection.executeQueryKey(key, null);

        log.debug("End execute. (key:{})", key);

        return key;
    }

    // 단순 조회 쿼리로 사용한다.

    /**
     * 쿼리를 실행하고 생성된 키를 반환한다.
     *
     * @param queryId
     * @param sqlParam
     * @return
     * @throws Throwable
     */
    public String execute(final String queryId, final Object sqlParam) throws Throwable {
        return execute(XDBProperties.SINGLE_KEY, queryId, sqlParam, -1L, -1L);
    }

    /**
     * 쿼리를 실행하고 생성된 키를 반환한다.
     *
     * @param queryId
     * @param sqlParam
     * @param startTime
     * @param endTime
     * @return
     * @throws Throwable
     */
    public String execute(final String queryId, final Object sqlParam,
                          final long startTime, final long endTime) throws Throwable {
        return execute(XDBProperties.SINGLE_KEY, queryId, sqlParam, startTime, endTime);
    }

    /**
     * 범위를 지정하여 조회하는 메서드로, execute 메서드 이후에 사용한다.
     *
     * @param generatedKey
     * @param recordOffset
     * @param recordCount
     * @return
     * @throws Throwable
     */
    public List<Map<String, String>> selectBounds(final String generatedKey,
                                                  final long recordOffset, final long recordCount) throws Throwable {
        return this.selectBounds(XDBProperties.SINGLE_KEY, generatedKey, recordOffset, recordCount);
    }

    public List<Map<String, String>> selectBounds(final Integer connectionId, final String generatedKey,
                                                  final long offset, final long limit) throws Throwable {
        return executeAwait(connectionManagementService.get(connectionId), generatedKey, offset, limit);
    }

    /**
     * 키 생성(GenerateQueryKey)을 한 뒤 데이터 조회(GetDataset) 메서드 호출하여 결과를 반환하는 메서드
     *
     * @param connectionId
     * @param queryId
     * @param sqlParam
     * @param startTime
     * @param endTime
     * @return
     * @throws Throwable
     */
    private List<Map<String, String>> executeAwait(final Integer connectionId, final String queryId, final Object sqlParam,
                                                   final Long startTime, final Long endTime) throws Throwable {
        final XDBConnection connection = connectionManagementService.get(connectionId);
        final String generatedKey = this.generateKey(connection, queryId, sqlParam, startTime, endTime);

        return executeAwait(connection, generatedKey, null, null);
    }

    /**
     * 동기화로 데이터 조회(GetDataset)하여 결과를 반환하는 메서드
     *
     * @param connection
     * @param key
     * @param offset
     * @param limit
     * @return
     * @throws Throwable
     */
    private List<Map<String, String>> executeAwait(final XDBConnection connection, final String key,
                                                   final Long offset, final Long limit)
        throws Throwable {

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final XDBExecuteQueryCallback callback = new XDBExecuteQueryCallback(countDownLatch);
        connection.executeQueryKey(key, callback);
        countDownLatch.await();

        final ExecutionResult executionResult = callback.result;

        log.info("Execution time: {}", executionResult.getExecutionTime());

        if (callback.throwable != null) { // 에러가 없으면 null 리턴하는 것으로 보임
            throw callback.throwable;
        } else {
            final Iterator<Dataset> iterator;
            final List<List<String>> records = new ArrayList<>();

            if (offset == null && limit == null) {
                iterator = connection.getDataset(key, 0, executionResult.getResultRowCount());
            } else {
                iterator = connection.getDataset(key, offset, limit);
            }

            iterator.forEachRemaining(dataset -> {
                records.add(dataset.getRecordList());
            });

            final TableSchemaResult tableSchemaResult = connection.getTableSchema(key);
            final List<String> columnNames = tableSchemaResult.getColumnInfoListList().stream()
                .map(column -> {
                    final String columnName = column.getColumnName();

                    return properties.isUnderscoreToCamelcase() ?
                        CaseUtils.toCamelCase(columnName, false, '_') : columnName;
                }).toList();

            final List<Map<String, String>> result = new ArrayList<>();
            records.forEach(record -> {
                final Map<String, String> r = new HashMap<>();

                for (int i = 0; i < columnNames.size(); i++) {
                    r.put(columnNames.get(i), record.get(i));
                }

                result.add(r);
            });

            return result;
        }
    }

    /**
     * 사용자 테이블에서 데이터를 조회한다.
     * startTime과 endTime 값이 -1일 경우 사용자 테이블에서 조회하고, 값이 있을 경우 ISA Hub에서 조회한다.
     *
     * @param queryId
     * @return
     * @throws Throwable
     */
    public List<Map<String, String>> selectUserTable(final String queryId, final Object sqlParam) throws Throwable {
        return this.executeAwait(XDBProperties.SINGLE_KEY, queryId, sqlParam, -1L, -1L);
    }

    /**
     * ISA Hub에서 데이터를 조회한다.
     * startTime과 endTime 값이 있을 경우 ISA Hub에서 조회하고, -1일 경우 사용자 테이블에서 조회한다.
     *
     * @param queryId
     * @param sqlParam
     * @param startTime
     * @param endTime
     * @return
     * @throws Throwable
     */
    public Object selectISAHub(final String queryId, final Object sqlParam,
                               final Long startTime, final Long endTime) throws Throwable {
        return this.executeAwait(XDBProperties.SINGLE_KEY, queryId, sqlParam, startTime, endTime);
    }

    /**
     * 생성된 키로 단순 조회한다.
     *
     * @param generatedKey 생성된 키
     * @return 쿼리 결과
     */
    public List<Map<String, String>> selectByKey(final String generatedKey) throws Throwable {
        return this.executeAwait(connectionManagementService.get(XDBProperties.SINGLE_KEY), generatedKey, null, null);
    }

    /**
     * 키 생성 및 반환한다.
     *
     * @param connection XDB Connection
     * @param queryId    Mybatis query ID
     * @param sqlParam
     * @param startTime
     * @param endTime
     * @return Generated key.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private String generateKey(final XDBConnection connection, final String queryId,
                            final Object sqlParam, final Long startTime, final Long endTime)
        throws NoSuchFieldException, IllegalAccessException {

        final String query = mapperUtil.getQuery(queryId, sqlParam);
        final GenerateResult generateResult = connection.generateQueryKey(
            // AuthenticationUtil.getCurrentUserId(),
            "admin", // FIXME: 테스트용으로 'admin'을 사용한다.
            query,
            startTime,
            endTime);

        return generateResult.getKey().getKeyString();
    }
}
