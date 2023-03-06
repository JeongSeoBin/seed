package com.innowireless.web.config;

import com.innowireless.web.util.MybatisKeyOrOffsetPaginationInterceptor;
import com.innowireless.web.util.MybatisPostgreSQLPaginationInterceptor;
import com.innowireless.web.util.MybatisPrintDBExecutionTimeInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h2>MyBatis 설정 클래스</h2>
 * <h3>Detecting MyBatis components</h3>
 * <p> The MyBatis-Spring-Boot-Starter will detects beans that
 * implements following interface provided by MyBatis.
 * <ul>
 *     <li>Interceptor</li>
 *     <li>TypeHandler</li>
 *     <li>LanguageDriver (Requires to use together with mybatis-spring 2.0.2+)</li>
 *     <li>DatabaseIdProvider</li>
 * </ul>
 */
@Configuration
@MapperScan("${mybatis.base-packages:com.innowireless.web.mapper}")
public class MybatisConfig {

    @Bean
    public MybatisKeyOrOffsetPaginationInterceptor paginationInterceptor() {
        return new MybatisKeyOrOffsetPaginationInterceptor();
    }

    @Bean
    public MybatisPostgreSQLPaginationInterceptor postgreSQLPaginationInterceptor() {
        return new MybatisPostgreSQLPaginationInterceptor();
    }

    @Bean
    public MybatisPrintDBExecutionTimeInterceptor printDBExecutionTimeInterceptor() {
        return new MybatisPrintDBExecutionTimeInterceptor();
    }

    // destroyMethod를 지정 안하면 server를 종료 할때 java.lang.UnsupportedOperationException:
    // Manual close is not allowed over a Spring managed SqlSession 이 WARN으로 로그에 찍힌다.
    @Bean(destroyMethod = "clearCache")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
