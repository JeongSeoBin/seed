package com.innowireless.web.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

// TODO: 사용하지 않을 경우 해당 클래스를 제거한다.
@Component
@ConditionalOnExpression("${project.test.alive-task2.enable:false}")
@Slf4j
public class AliveTask2 extends DynamicScheduler {

    @Value("${project.test.alive-task2.time}")
    private String expression;

    @PostConstruct
    public void init() {
        startScheduler();
    }

    @Override
    void runner() {
        try {
            log.debug("[AliveTask2] I'm alive!");
        } catch (Throwable throwable) {
            log.error(ExceptionUtils.getStackTrace(throwable));
        }
    }

    @Override
    protected Trigger getTrigger() {
        return new CronTrigger(expression);
    }
}
