package com.innowireless.web.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 사용하지 않을 경우 해당 클래스를 제거한다.
 */
@Component
@Slf4j
public class TestTask1 extends DynamicScheduler {

    @Value("${test.task}")
    private String expression;

    @PostConstruct
    public void init() {
        startScheduler();
    }

    @Override
    void runner() {
        try {
            log.info("I'm alive! in runner");
        } catch (Throwable throwable) {
            log.error(ExceptionUtils.getStackTrace(throwable));
            log.error("failed to run deleteAllAnalysisTimeForMonth", throwable.getMessage());
        }
    }

    @Override
    protected Trigger getTrigger() {
        return new CronTrigger(expression);
    }
}
