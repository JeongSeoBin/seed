package com.innowireless.web.util;

import com.innowireless.web.api.ApiException;
import com.innowireless.web.api.ErrorCodes;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class CommonUtil {

    // java.time.format.DateTimeFormatter
    public static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER2 =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter LOCAL_DATE_FORMATTER =
        DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter LOCAL_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("HHmmss");

    public static String getClientIpAddr(final HttpServletRequest request) {
        // 참고
        // http://stackoverflow.com/questions/4678797/how-do-i-get-the-remote-address-of-a-client-in-servlet%20

        String ip = request.getHeader("X-Forwarded-For");
        String unknown = "unknown";

        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }


    public static String getExceptionReasons(final Throwable e) {
        if (e == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Throwable cause = null;
        Throwable result = e;

        sb.append(e.getMessage());
        int cnt = 0;
        while (null != (cause = result.getCause()) && result != cause) {
            sb.append(" (").append(cause.getMessage());
            result = cause;
            cnt++;
        }

        for (int i = 0; i < cnt; i++) {
            sb.append(")");
        }

        return sb.toString();
    }

    public static String urlEncode(final String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    /**
     * 지정 소수점 아래 자리 수 까지 반올림
     *
     * @param base
     * @param decimalPlace 소수점 아래 자리
     * @return
     */
    public static Double roundWithDecimalPlace(final Double base, final int decimalPlace) {
        if (base == null) {
            return null;
        }

        if (decimalPlace < 0) {
            throw new ApiException(ErrorCodes.INTERNAL_ERROR, "decimalPlace < 0");
        }

        final double e = Math.pow(10, decimalPlace);
        return Math.round(base * e) / e;
    }

    // Cron Expression 참고 사이트
    // https://docs.oracle.com/cd/E12058_01/doc/doc.1014/e12030/cron_expressions.htm
    public static String getCronExpression(final String time) {
        if (!ValidationUtil.isTimeString(time)) {
            throw new ApiException(ErrorCodes.INTERNAL_ERROR, "invalid time str");
        }

        final String hourStr = StringUtils.substring(time, 0, 2);
        final String minStr = StringUtils.substring(time, 2, 4);
        final String secStr = StringUtils.substring(time, 4, 6);

        return String.format("%d %d %d * * *",
            Integer.parseInt(secStr), Integer.parseInt(minStr), Integer.parseInt(hourStr));
    }

    public static String escapeJsString(final String s) {
        return s
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("'", "\\'")
            .replace("\r", "\\r")
            .replace("\n", "\\n");
    }

    public static String toNotNullString(final String s) {
        return (s == null) ? "" : s;
    }
}
