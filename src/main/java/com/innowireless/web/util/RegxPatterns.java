package com.innowireless.web.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RegxPatterns {

    /**
     * 공백 비허용
     */
    public static final String PATTERN_NON_BLANK = "[\\S]+";

    /**
     * 문자, 숫자, '.', '_', 공백 허용. 맨앞에 -가 올수 있음 음수를 표현하기 위함. <br>
     * 빈 문자열도 허용한다.
     */
    public static final String PATTERN_BASIC_SQL_PARAM = "^$|^-?[\\w\\d\\s\\.]+";

    /**
     * 문자, 숫자, '_' 허용.
     */
    public static final String PATTERN_COLUMN_NAME = "[\\w\\d]+";

    /**
     * 사용자 ID 체크 정규식. 문자, 숫자, '_', '.' 3~20자리 허용
     */
    public static final String PATTERN_USER_ID = "[\\w\\d\\.]{3,20}";
}
