package com.innowireless.web.util;

import com.innowireless.web.api.ApiException;
import com.innowireless.web.api.ErrorCodes;
import lombok.experimental.UtilityClass;
import org.apache.ibatis.session.RowBounds;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@UtilityClass
public class DBUtil {

    private static final String TIMESTAMP_ERROR_MSG = "unsupported timestamp type";

    public static Long convertToUnixTimestamp(String dTime) {
        return convertToUnixTimestamp(dTime, CommonUtil.LOCAL_DATE_TIME_FORMATTER);
    }

    public static Long convertToUnixTimestamp(String dTime, DateTimeFormatter formatter) {
        LocalDateTime temp = LocalDateTime.parse(dTime, formatter);
        return temp.atZone(TimeZone.getDefault().toZoneId()).toEpochSecond();
    }

    /**
     * 검색 결과의 pagination을 위한 RowBounds를 생성한다.
     */
    public static RowBounds createRowBounds(Integer offset, Integer count) {
        if (offset == null && count == null) {
            return RowBounds.DEFAULT;
        }

        return new RowBounds(
            (offset == null) ? RowBounds.NO_ROW_OFFSET : offset,
            (count == null) ? RowBounds.NO_ROW_LIMIT : count);
    }

    //---------------------------------------------------------------------------------------------
    // List<Map<String, Object>> 에서 특정 칼럼을 추출하는 메소드
    // 오라클은 숫자타입들이 BigDecimal로 들어오기 때문에 제네릭으로 만들어도 쓰기 애매해서
    // 그냥 각 타입별로 메소드 만들어 준다.
    //---------------------------------------------------------------------------------------------

    /**
     * query 결과인 List<Map<String, Object>> 형 데이터에서 특정 map 키에 대한 String list를
     * 추출 한다.
     *
     * @param source
     * @param key
     * @return
     */
    public static List<String> extractStringList(List<Map<String, Object>> source, String key) {
        List<String> rs = new ArrayList<>();

        if (source == null) {
            return rs;
        }

        for (Map<String, Object> row : source) {
            if (row == null) {
                continue;
            }

            Object obj = row.get(key);

            if (obj == null) {
                continue;
            }

            String temp = String.valueOf(obj);
            rs.add(temp);
        }

        return rs;
    }

    /**
     * query 결과인 List<Map<String, Object>> 형 데이터에서 특정 map 키에 대한 Long list를
     * 추출 한다.
     *
     * @param source
     * @param key
     * @return
     */
    public static List<Long> extractLongList(List<Map<String, Object>> source, String key) {
        List<Long> rs = new ArrayList<>();

        if (source == null) {
            return rs;
        }

        for (Map<String, Object> row : source) {
            if (row == null) {
                continue;
            }

            Object obj = row.get(key);

            if (obj == null) {
                continue;
            }

            Long temp = toLong(obj);
            rs.add(temp);
        }

        return rs;
    }

    //---------------------------------------------------------------------------------------------
    // null 허용하지 않는 형 변환 메소드
    //---------------------------------------------------------------------------------------------

    public static String toNotNullString(Object obj) {
        return (obj == null) ? "" : (String) obj;
    }

    public static int toNotNullInt(Object obj) {
        return (obj == null) ? 0 : ((BigDecimal) obj).intValue();
    }

    public static double toNotNullDouble(Object obj) {
        return (obj == null) ? 0 : ((BigDecimal) obj).doubleValue();
    }

    public static long toNotNullLong(Object obj) {
        return (obj == null) ? 0 : ((BigDecimal) obj).longValue();
    }

    public static boolean toNotNullBoolean(Object obj) {
        return obj != null && (boolean) obj;
    }

    public static LocalDateTime toLocalDateTime(Object obj) {
        if (obj == null) {
            return null;
        }

        if (obj instanceof String) { // yyyyMMddHHmmss String
            return LocalDateTime.parse((String) obj, CommonUtil.LOCAL_DATE_TIME_FORMATTER);
        } else if (obj instanceof BigDecimal) { // millisecond
            return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(toNotNullLong(obj)), ZoneId.systemDefault());
        } else if (obj instanceof java.sql.Timestamp) {
            return ((java.sql.Timestamp) obj).toLocalDateTime();
        } else {
            throw new ApiException(ErrorCodes.INTERNAL_ERROR, TIMESTAMP_ERROR_MSG);
        }
    }

    public static LocalDate toLocalDate(Object obj) {
        if (obj == null)
            return null;

        if (obj instanceof String) { // YYYYMMDD String
            return LocalDate.parse((String) obj, CommonUtil.LOCAL_DATE_FORMATTER);
        } else if (obj instanceof BigDecimal) { // millisecond
            return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(toNotNullLong(obj)), ZoneId.systemDefault()).toLocalDate();
        } else if (obj instanceof java.sql.Timestamp) {
            return ((java.sql.Timestamp) obj).toLocalDateTime().toLocalDate();
        } else {
            throw new ApiException(ErrorCodes.INTERNAL_ERROR, TIMESTAMP_ERROR_MSG);
        }
    }

    public static LocalTime toLocalTime(Object obj) {
        if (obj == null) {
            return null;
        }

        if (obj instanceof String) { // HHmmss String
            return LocalTime.parse((String) obj, CommonUtil.LOCAL_TIME_FORMATTER);
        } else if (obj instanceof BigDecimal) { // millisecond
            return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(toNotNullLong(obj)), ZoneId.systemDefault()).toLocalTime();
        } else if (obj instanceof java.sql.Timestamp) {
            return ((java.sql.Timestamp) obj).toLocalDateTime().toLocalTime();
        } else {
            throw new ApiException(ErrorCodes.INTERNAL_ERROR, TIMESTAMP_ERROR_MSG);
        }
    }

    //---------------------------------------------------------------------------------------------
    // null을 감안해서 변환하는 메소드들
    //---------------------------------------------------------------------------------------------

    public static Integer toInteger(Object obj) {
        return (obj == null) ? null : ((BigDecimal) obj).intValue();
    }

    public static Long toLong(Object obj) {
        return (obj == null) ? null : ((BigDecimal) obj).longValue();
    }

    public static Double toDouble(Object obj) {
        return (obj == null) ? null : ((BigDecimal) obj).doubleValue();
    }

    //---------------------------------------------------------------------------------------------
    // java8의 java.time type을 java.sql.Date/Time 형태로 바꿔주는 메소드
    //---------------------------------------------------------------------------------------------

    public static Long msecEpochtimeToSqlTimestamp(Long epochTime) {
        if (epochTime == null) {
            return null;
        }

        return (long) Math.floor(epochTime / 1000.0);
    }

    public static Long msecEpochtimeToSqlTimestamp(String epochTime) {
        if (epochTime == null) {
            return null;
        }

        return (long) Math.floor(Long.parseLong(epochTime) / 1000.0);
    }

    public static java.sql.Timestamp toSqlTimestamp(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }

        return java.sql.Timestamp.valueOf(dateTime);
    }

    public static java.sql.Date toSqlDate(LocalDate date) {
        if (date == null) {
            return null;
        }

        return java.sql.Date.valueOf(date);
    }

    public static java.sql.Time toSqlTime(LocalTime time) {
        if (time == null) {
            return null;
        }

        return java.sql.Time.valueOf(time);
    }

    // ============================================================================================
    // PreparedStatement를 직접 사용하는 경우
    // ============================================================================================

    public static class PreparedStatementArgumentSetter {

        private final PreparedStatement ps;
        private int parameterIndex;

        public PreparedStatementArgumentSetter(PreparedStatement ps) {
            this.ps = ps;
            parameterIndex = 1;
        }

        private void psSetInt(int parameterIndex, Integer value) throws SQLException {
            if (value == null) {
                ps.setNull(parameterIndex, java.sql.Types.INTEGER);
            } else {
                ps.setInt(parameterIndex, value);
            }
        }

        private void psSetBoolean(int parameterIndex, Boolean value) throws SQLException {
            if (value == null) {
                ps.setNull(parameterIndex, java.sql.Types.BOOLEAN);
            } else {
                ps.setBoolean(parameterIndex, value);
            }
        }

        public void setNext(String value) throws SQLException {
            ps.setString(parameterIndex++, value);
        }

        public void setNext(Integer value) throws SQLException {
            psSetInt(parameterIndex++, value);
        }

        public void setNext(Boolean value) throws SQLException {
            psSetBoolean(parameterIndex++, value);
        }

        public void setNext(LocalDateTime value) throws SQLException {
            ps.setTimestamp(parameterIndex++, DBUtil.toSqlTimestamp(value));
        }

        public void setNext(LocalDate value) throws SQLException {
            ps.setDate(parameterIndex++, DBUtil.toSqlDate(value));
        }

        public void setNext(LocalTime value) throws SQLException {
            ps.setTime(parameterIndex++, DBUtil.toSqlTime(value));
        }
    }
}
