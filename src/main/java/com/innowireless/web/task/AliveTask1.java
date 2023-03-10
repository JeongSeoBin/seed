package com.innowireless.web.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// TODO: 사용하지 않을 경우 해당 클래스를 제거한다.
@Component
@Slf4j
@RequiredArgsConstructor
public class AliveTask1 {

    // @Async
    @Scheduled(cron = "${project.test.alive-task1.time:-}")
    public void print() {
        log.debug("[AliveTask1] I'm alive!");
    }
}
