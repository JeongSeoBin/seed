package com.innowireless.web.api;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorCodes {

    // @formatter:off
    public static final int GENERIC                                 = 2000;
    public static final int INVALID_REQUEST                         = 2001;
    public static final int INVALID_ARGUMENT                        = 2002;
    public static final int MISSING_REQUIRED_FIELD                  = 2003;
    public static final int DB_ERROR                                = 2004;
    public static final int INTERNAL_ERROR                          = 2005;
    public static final int NOT_FOUND                               = 2006;
    public static final int PERMISSION_DENIED                       = 2007;
    public static final int DATA_ERROR                              = 2008;
    public static final int DUPLICATED_DATA                         = 2009;
    public static final int INTEGRITY_VIOLATION_ERROR               = 2010;

    //=============================================================================================
    // 인증 관련
    //=============================================================================================

    public static final int NEED_LOGIN                              = 2100;
    public static final int BAD_CREDENTIALS_ERROR                   = 2101;
    public static final int LOGIN_ERROR                             = 2102;
    public static final int ACCOUNT_STATUS_DISABLED                 = 2103;
    public static final int ACCOUNT_STATUS_LOCKED                   = 2104;
    public static final int ACCOUNT_STATUS_EXPIRED                  = 2105;
    public static final int NO_USER_ROLE                            = 2106;
    public static final int PASSWORD_RULE_INVALID                   = 2107;
    public static final int USER_NAME_NOT_FOUND                     = 2108;
    public static final int INVALID_IP                              = 2109;
    public static final int NEED_PASSWORD_CHANGE                    = 2110;
    public static final int PASSWORD_MISMATCH                       = 2111;
    public static final int NO_APPROVED_USER                        = 2112;

    //=============================================================================================
    // 서비스 관리
    //=============================================================================================

    public static final int SVC_CSV_FAIL_COUNT_TOTAL_COUNT          = 2141;

    //=============================================================================================
    // gRPC, Wolfdog에서 주는 오류 관련
    //=============================================================================================

    public static final int GRPC_STATUS                             = 2200;
    // 이 값부터 2299까지를 reserve 한다. io.grpc.Status.java를 보면 0~16까지 실제 값이 존재한다.

    // WAS에서 필요해서 정의한 에러코드
    public static final int NO_AVAILABLE_WOLFDOG                    = 2290;
    public static final int INVALID_WOLFDOG                         = 2291;
    public static final int NOT_FOUND_QUERY_KEY                     = 2292;
    public static final int NO_SUCH_TABLE                           = 2293;

    public static final int STOP_QUERY                              = 2299;

    //=============================================================================================
    //
    //=============================================================================================


    //=============================================================================================
    //
    //=============================================================================================


    //=============================================================================================
    //
    //=============================================================================================


    //=============================================================================================
    // XDB
    //=============================================================================================

    public static final int XDB_RESULT                              = 2600;
    // 이 값을 BaseResult.ResultCode 값에 더해서 사용한다.
    public static final int INVALID_XDB                             = 2601;
    public static final int NO_AVAILABLE_XDB                        = 2602;
    // @formatter:on
}
