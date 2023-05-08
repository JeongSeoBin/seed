package com.innowireless.web.util.xdb;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Configuration
@ConfigurationProperties(prefix = XDBProperties.XDB_PREFIX)
@ConditionalOnProperty(prefix = XDBProperties.XDB_PREFIX, name = "enable", havingValue = "true")
@Getter
@Setter
@FieldDefaults(level = PRIVATE)
@ToString
public class XDBProperties {

    public static final String XDB_PREFIX = "xdb";
    public static final Integer SINGLE_KEY = Integer.MAX_VALUE; // 단일 XDB 사용 시 해당 키로 조회한다.

    boolean enable;
    Config config;
    List<Config> configs;
    boolean underscoreToCamelcase;

    public void setConfig(Config config) {
        this.config = config;
        this.config.setId(SINGLE_KEY);
    }

    public void setConfigs(List<Config> configs) {
        this.configs = configs;

        if (configs.size() == 1) {
            this.configs.get(0).setId(SINGLE_KEY);
        }
    }

    @Getter
    @Setter
    @FieldDefaults(level = PRIVATE)
    @ToString
    public static class Config {
        Integer id; // TODO: 우선 5GCP와 동일하게 Integer로 사용한다.
        String host;
        Integer port;
    }
}
