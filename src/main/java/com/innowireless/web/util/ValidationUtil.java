package com.innowireless.web.util;

import com.innowireless.web.api.ApiException;
import com.innowireless.web.api.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ValidationUtil {

    private ValidationUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * annotation를 이용한 validation 체크 결과에서 error가 존재 할 때 Exception을
     * 발생시킨다.
     *
     * @param result annotation를 이용한 validation 체크 결과
     */
    public static void checkAnnotationValidationResult(BindingResult result) {
        if (result == null || !result.hasErrors())
            return;

        ArrayList<String> errMsgs = new ArrayList<>();
        List<ObjectError> list = result.getAllErrors();
        for (ObjectError e : list) {
            log.error("ObjectError: {}", e);
            errMsgs.add(((FieldError) e).getField()); // error field들을 모은다.
        }

        throw new ApiException(ErrorCodes.MISSING_REQUIRED_FIELD, StringUtils.join(errMsgs, ", "));
    }

    /**
     * 주어진 str이 숫자인지 체크
     *
     * @param str - 대상 값
     * @return boolean
     */
    public static boolean isStringNumeric(String str) {
        if (StringUtils.isEmpty(str))
            return false;

        return str.chars().allMatch(Character::isDigit);
    }

    /**
     * 입력한 모든 String이 같은가 체크. <br>
     * 입력 값이 두개미만인 경우 return false
     *
     * @param strs - 대상 값들
     * @return 모두 같으면 true, 그렇지 않으면 false
     */
    public static boolean isAllStrEquals(String... strs) {
        if (strs == null || strs.length < 2)
            return false;

        String first = strs[0];
        for (String str : strs) {
            if (!StringUtils.equals(first, str))
                return false;
        }

        return true;
    }

    /**
     * 입력된 List<?>들 중에 하나라도 null혹은 비었는지 판단
     *
     * @param objs
     * @return
     */
    public static boolean isAnyListEmpty(List<?>... objs) {
        if (objs == null || objs.length == 0)
            return true;

        boolean isEmpty = false;
        for (List<?> obj : objs) {
            isEmpty |= (obj == null || obj.isEmpty());
        }

        return isEmpty;
    }

    /**
     * 입력된 List<?> 맴버가 null이 포함되었는지 체크
     *
     * @param list
     * @return
     */
    public static boolean isListWithNull(List<?> list) {
        if (list == null || list.isEmpty())
            return true;

        for (Object obj : list) {
            if (obj == null)
                return true;
        }

        return false;
    }

    /**
     * target String이 주어진 String[]에 포함되어 있는지 여부
     *
     * @param target 비교 대상
     * @param words  비교 할 목록
     * @return
     */
    public static boolean isStrIncludeIn(String target, String... words) {
        if (target == null || words.length == 0)
            return false;

        return Arrays.asList(words).contains(target);
    }

    /**
     * String list내에 주어진 words들중 하나라도 가지고 있는지 여부 <br>
     *
     * @param list
     * @param words null이 포함되면 null은 skip
     * @return
     */
    public static boolean isListContainsAnyStr(List<String> list, String... words) {
        if (list == null || list.isEmpty() || words == null || words.length == 0)
            return false;

        for (String temp : words) {
            if (temp == null)
                continue;

            if (list.contains(temp))
                return true;
        }

        return false;
    }

    /**
     * values 파라메터 중에 오직 하나의 non blank String이 포함하는지 체크 <br>
     * <pre>
     * ValidationUtil.isContainsOnlyOneNoneBlankStr("abc")                = true
     * ValidationUtil.isContainsOnlyOneNoneBlankStr("")                   = false
     * ValidationUtil.isContainsOnlyOneNoneBlankStr("", " ")              = false
     * ValidationUtil.isContainsOnlyOneNoneBlankStr(null, "")             = false
     * ValidationUtil.isContainsOnlyOneNoneBlankStr("a", "b")             = false
     * ValidationUtil.isContainsOnlyOneNoneBlankStr(null, "abc")          = true
     * ValidationUtil.isContainsOnlyOneNoneBlankStr(null, null)           = false
     * ValidationUtil.isContainsOnlyOneNoneBlankStr(null, null, "abc")    = true
     * ValidationUtil.isContainsOnlyOneNoneBlankStr(null, null, "a", "")  = true
     * ValidationUtil.isContainsOnlyOneNoneBlankStr(" ", "", "a", "")     = true
     * ValidationUtil.isContainsOnlyOneNoneBlankStr(" ", "", "", "")      = false
     * ValidationUtil.isContainsOnlyOneNoneBlankStr(" ", "a", "b", "")    = false
     * </pre>
     *
     * @param values
     * @return 오직 하나의 non blank String이 포함될 때 true, 그렇지 않으면 false
     */
    public static boolean isContainsOnlyOneNoneBlankStr(String... values) {
        if (values == null)
            return false;

        int count = 0;
        for (String value : values) {
            if (StringUtils.isNotBlank(value))
                count++;
            if (count > 1)
                return false;
        }

        return (count == 1);
    }

    public static boolean isTwoDoubleEqual(Double a, Double b) {
        if (a == null && b == null)
            return true;
        else if (a == null)
            return false;
        else if (b == null)
            return false;
        else
            return a.equals(b);
    }

    public static boolean isTwoIntegerEqual(Integer a, Integer b) {
        if (a == null && b == null)
            return true;
        else if (a == null)
            return false;
        else if (b == null)
            return false;
        else
            return a.equals(b);
    }

    public static boolean isNumericType(Object obj) {
        return ((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double));
    }

    /**
     * 입력 String이 yyyyMMddHHmmss 형식인지 체크
     *
     * @param dateTimeStr
     * @return
     */
    public static boolean isDateTimeString(String dateTimeStr) {
        if (StringUtils.isEmpty(dateTimeStr))
            return false;

        try {
            LocalDateTime.parse(dateTimeStr, CommonUtil.LOCAL_DATE_TIME_FORMATTER);
        } catch (Exception e) {
            return false;
        }

        return true;
    }


    /**
     * 입력 String이 yyyyMMdd 형식인지 체크
     *
     * @param dateStr
     * @return
     */
    public static boolean isDateString(String dateStr) {
        if (StringUtils.isEmpty(dateStr))
            return false;

        try {
            LocalDate.parse(dateStr, CommonUtil.LOCAL_DATE_FORMATTER);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * 입력 String이 HHmmss 형식인지 체크
     *
     * @param timeStr
     * @return
     */
    public static boolean isTimeString(String timeStr) {
        if (StringUtils.isEmpty(timeStr))
            return false;

        try {
            LocalTime.parse(timeStr, CommonUtil.LOCAL_TIME_FORMATTER);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * 주어진 DateTimeFormatter 에 맞는 시간 스트링인지 판단.
     *
     * @param dTime
     * @param dtf
     * @return
     */
    public static boolean isDTimeString(String dTime, DateTimeFormatter dtf) {
        if (StringUtils.isEmpty(dTime))
            return false;

        try {
            LocalTime.parse(dTime, dtf);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static void checkStartDtimeEndDtime(String sDtime, String eDtime) {
        if (!isDateTimeString(sDtime))
            throw new ApiException(ErrorCodes.INVALID_ARGUMENT, "sTime");

        if (!isDateTimeString(eDtime))
            throw new ApiException(ErrorCodes.INVALID_ARGUMENT, "eTime");
    }

    public static boolean isIpCClassAddressEqual(String allowedIp, String userIp) {
        try {
            String[] splitAllowedIp = allowedIp.split("\\.");
            String[] splitUserIpCClass = userIp.split("\\.");
            if (splitAllowedIp.length != splitUserIpCClass.length)
                return false;
            for (int i = 0; i < splitAllowedIp.length - 1; i++) {
                if (!splitAllowedIp[i].equals(splitUserIpCClass[i])) {
                    return false;
                }
            }
            return true;
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}
