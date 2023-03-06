package com.innowireless.web.config.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static lombok.AccessLevel.PRIVATE;

@Configuration
@ConfigurationProperties(ProjectProperties.PROJECT_PREFIX)
@Getter
@Setter
@FieldDefaults(level = PRIVATE)
@ToString
public class ProjectProperties {

    public static final String PROJECT_PREFIX = "project";

    String baseDir;
}
