package com.innowireless.web.util.xdb;

import com.innowireless.xdb5gcp.rui.ExecutionResult;
import lombok.Getter;

/**
 * XDB 쿼리 결과 저장
 */
@Getter
public class XDBClientResult {

    private String key;

    private int sqlType;
    private int executionStatus;
    private int executionErrorCode;
    private String executionMessage;
    private long executionTime;  // msec 단위.
    private long resultRowCount;

    public XDBClientResult(final ExecutionResult src) {
        this.sqlType = src.getSqlTypeValue();
        this.executionStatus = src.getExecutionStatusValue();
        this.executionErrorCode = src.getExecutionErrorCodeValue();
        this.executionMessage = src.getExecutionMessage();
        this.executionTime = src.getExecutionTime();
        this.resultRowCount = src.getResultRowCount();
    }

    public XDBClientResult(final String key, final ExecutionResult src) {
        this(src);
        this.key = key;
    }
}
