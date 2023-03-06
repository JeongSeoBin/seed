package com.innowireless.web.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 스케줄러를 유연하게 동작시키기 위해서 DynamicScheduler 클래스를 상속 받아 사용한다.
 */
@Slf4j
public abstract class DynamicScheduler {

    private ThreadPoolTaskScheduler scheduler;

    protected void startScheduler() {
        log.info("start {} Scheduler", getClass().getName());

        scheduler = new ThreadPoolTaskScheduler();
        scheduler.initialize();
        scheduler.schedule(getRunnable(), getTrigger());
    }

    protected void stopScheduler() {
        if (scheduler != null) {
            log.info("stop {} Scheduler", getClass());
            scheduler.shutdown();
            scheduler = null;
        }
    }

    public void restartScheduler() {
        stopScheduler();
        startScheduler();
    }

    private Runnable getRunnable() {
        return this::runner;
    }

    abstract protected Trigger getTrigger();

    abstract void runner();
}
