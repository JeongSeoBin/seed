package com.innowireless.web.util;

import lombok.experimental.UtilityClass;

/**
 * spring.security.custom 속성에 추가하여 사용하도록 한다.
 */
@Deprecated
@UtilityClass
public class PageUrls {

    // @formatter:off
    public static final String INDEX_PAGE_URL        = "/index.html";
    public static final String DIRECT_LOGIN_PAGE_URL = "/login.html";
    public static final String ISO_LOGIN_PAGE_URL    = "/loginiso.html";

    private static final String LOGIN_URL_PREFIX     = "/api/anon/login/";
    public static final String LOGIN_URL_PATTERN     = LOGIN_URL_PREFIX + "**";
    public static final String DIRECT_LOGIN_URL      = LOGIN_URL_PREFIX + "direct";
    public static final String ISO_LOGIN_URL         = LOGIN_URL_PREFIX + "iso";

    public static final String LOGOUT_PROCESS_URL    = "/api/anon/logout";
    // @formatter:on
}
