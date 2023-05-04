package com.innowireless.web.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * MyBatis mapper.xml에 작성된 쿼리를 조회하여 파싱 후 반환한다.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MybatisMapperUtil {

    private final SqlSession sqlSessionTemplate;

    public String getQuery(final String queryId, final Object sqlParam)
        throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        final BoundSql boundSql = sqlSessionTemplate.getConfiguration()
            .getMappedStatement(queryId).getBoundSql(sqlParam);
        String sql = boundSql.getSql();

        if (sqlParam == null) {
            sql = sql.replaceFirst("\\?", "''");
        } else {
            if (sqlParam instanceof Number) { // Integer, Long, Float, Double 등의 숫자형 데이터 클래스는 Number 클래스를 상속받기 때문에 Number로 체크한다
                sql = doQueryMappingNumber(sql, sqlParam);
            } else if (sqlParam instanceof String) {
                sql = doQueryMappingString(sql, sqlParam);
            } else if (sqlParam instanceof Map) {        // 해당 파라미터가 Map 일 경우
                sql = doQueryMappingMap(sql, sqlParam, boundSql);
            } else { // 해당 파라미터가 사용자 정의 클래스일 경우
                sql = doQueryMappingClass(sql, sqlParam, boundSql);
            }
        }

        return sql;
    }

    /*
     * 쿼리의 ?와 매핑되는 실제 값들이 List 객체로 return이 된다.
     * 이때 List 객체의 0번째 순서에 있는 ParameterMapping 객체가 쿼리의 첫번째 ?와 매핑이 된다
     * 이런 식으로 쿼리의 ?과 ParameterMapping 객체들을 Mapping 한다
     */
    private String doQueryMappingClass(String sql, Object sqlParam, BoundSql boundSql)
        throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        String ret = sql;
        List<ParameterMapping> paramMapping = boundSql.getParameterMappings();
        Class<?> paramClass = sqlParam.getClass();

        for (ParameterMapping mapping : paramMapping) {
            String propValue = mapping.getProperty();                   // 해당 파라미터로 넘겨받은 사용자 정의 클래스 객체의 멤버변수명
            Field field = doDeclaredField(paramClass, propValue);       // 관련 멤버변수 Field 객체 얻어옴
            if (field == null) {                                         // 최상위 클래스까지 갔는데도 필드를 찾지 못할경우엔 null이 return 되기 때문에 null을 return 하게 되면 NoSuchFieldException 예외를 던진다
                throw new NoSuchFieldException();
            }
            field.setAccessible(true);                    // 멤버변수의 접근자가 private일 경우 reflection을 이용하여 값을 해당 멤버변수의 값을 가져오기 위해 별도로 셋팅
            Class<?> javaType = mapping.getJavaType();            // 해당 파라미터로 넘겨받은 사용자 정의 클래스 객체의 멤버변수의 타입

            /* // column name은 "'"가 들어가면 안되기 때문에 주석
            if (String.class == javaType) {                // SQL의 ? 대신에 실제 값을 넣는다. 이때 String 일 경우는 '를 붙여야 하기땜에 별도 처리
                sql = sql.replaceFirst("\\?", "'" + field.get(sqlParam) + "'");
            } else {
                sql = sql.replaceFirst("\\?", field.get(sqlParam).toString());
            }*/
            sql = sql.replaceFirst("\\?", field.get(sqlParam).toString());
        }

        return ret;
    }

    /*
     * 쿼리의 ?와 매핑되는 실제 값들의 정보가 들어있는 ParameterMapping 객체가 들어간 List 객체로 return이 된다.
     * 이때 List 객체의 0번째 순서에 있는 ParameterMapping 객체가 쿼리의 첫번째 ?와 매핑이 된다
     * 이런 식으로 쿼리의 ?과 ParameterMapping 객체들을 Mapping 한다
     */
    private String doQueryMappingMap(String sql, Object sqlParam, BoundSql boundSql) {
        String ret = sql;
        List<ParameterMapping> paramMapping = boundSql.getParameterMappings();

        for (ParameterMapping mapping : paramMapping) {
            String propValue = mapping.getProperty();        // 파라미터로 넘긴 Map의 key 값이 들어오게 된다
            Object value = ((Map<?, ?>) sqlParam).get(propValue);    // 넘겨받은 key 값을 이용해 실제 값을 꺼낸다

            /* // column name은 "'"가 들어가면 안되기 때문에 주석
            if (value instanceof String) {            // SQL의 ? 대신에 실제 값을 넣는다. 이때 String 일 경우는 '를 붙여야 하기땜에 별도 처리
                ret = ret.replaceFirst("\\?", "'" + value + "'");
            } else {
                ret = ret.replaceFirst("\\?", value.toString());
            }*/

            if (value == null) {
                // __frch_ PREFIX에 대한 처리
                if (boundSql.hasAdditionalParameter(propValue)) {  // myBatis가 추가 동적인수를 생성했는지 체크하고
                    value = boundSql.getAdditionalParameter(propValue);  // 해당 추가 동적인수의 Value를 가져옴
                }
            }

            ret = ret.replaceFirst("\\?", value.toString());
        }

        return ret;
    }

    private String doQueryMappingString(String sql, Object sqlParam) {
         /* // column name은 "'"가 들어가면 안되기 때문에 주석
         return sql.replaceFirst("\\?", "'" + sqlParam + "'");
         */
        return sql.replaceFirst("\\?", sqlParam.toString());
    }

    private String doQueryMappingNumber(String sql, Object sqlParam) {
        return sql.replaceFirst("\\?", sqlParam.toString());
    }

    /**
     * 클래스 필드 검색 재귀함수
     *
     * @param paramClass
     * @param propValue
     * @return
     */
    private Field doDeclaredField(Class<?> paramClass, String propValue) {
        Field field = null;

        try {
            /*
             * 해당 객체의 필드를 검색 한다.
             * 존재 하지 않을 경우 NoSuchFieldException 발생
             */
            field = paramClass.getDeclaredField(propValue);

        } catch (NoSuchFieldException e) {
            // NoSuchFieldException 발생 할경우 상위 클래스를 검색 한다.
            field = doDeclaredField(paramClass.getSuperclass(), propValue);
        }

        return field;
    }
}
