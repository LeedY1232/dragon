package com.dragon.server.common;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class GlobalTimerService {
    private static final Logger logger = LoggerFactory.getLogger(GlobalTimerService.class);

    private ScheduledExecutorService executor;

    @PostConstruct
    public void init() {
        executor = Executors.newScheduledThreadPool(3);
    }

    public void schedule(Runnable command, long initialDelaySec, long periodSec) {
        executor.scheduleWithFixedDelay(command, initialDelaySec, periodSec, TimeUnit.SECONDS);
    }

    @PreDestroy
    private void destroy() {
        if (executor != null) {
            try {
                executor.shutdown();
                logger.info("Shutdown GlobalTimerService.");
            } catch (Throwable e) {
                logger.info("Shutdown GlobalTimerService failed.", e);
            }
        }
    }
}
