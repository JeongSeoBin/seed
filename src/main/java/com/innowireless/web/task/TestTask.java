package com.innowireless.web.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 사용하지 않을 경우 해당 클래스를 제거한다.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class TestTask {

    @Async
    @Scheduled(cron = "${test.task:-}")
    public void print() {
        log.info("I'm alive!");
    }

    @Async
    @Scheduled(cron = "${test.task:-}")
    public void print1() {
        log.info("[{}] I'm alive!", "print1");
    }

    @Async
    @Scheduled(cron = "${test.task:-}")
    public void print2() {
        log.info("[{}] I'm alive!", "print2");
    }

    @Async
    @Scheduled(cron = "${test.task:-}")
    public void print3() {
        log.info("[{}] I'm alive!", "print3");
    }

    @Async
    @Scheduled(cron = "${test.task:-}")
    public void print4() {
        log.info("[{}] I'm alive!", "print4");
    }

    @Async
    @Scheduled(cron = "${test.task:-}")
    public void print5() {
        log.info("[{}] I'm alive!", "print5");
    }

    @Async
    @Scheduled(cron = "${test.task:-}")
    public void print6() {
        log.info("[{}] I'm alive!", "print6");
    }

    @Async
    @Scheduled(cron = "${test.task:-}")
    public void print7() {
        log.info("[{}] I'm alive!", "print7");
    }

    @Async
    @Scheduled(cron = "${test.task:-}")
    public void print8() {
        log.info("[{}] I'm alive!", "print8");
    }

    @Async
    @Scheduled(cron = "${test.task:-}")
    public void print9() {
        log.info("[{}] I'm alive!", "print9");
    }
}
