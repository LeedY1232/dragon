package com.dragon.server.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GlobalExecService {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExecService.class);

    private static final int CORE_POOL_SIZE = 15;
    private static final int MAX_POOL_SIZE = 30;
    private static final long KEEP_ALIVE_TIME = 30L;
    private static final int QUEUE_SIZE = 5000;

    private ExecutorService executor;

    @PostConstruct
    private void init() {
        executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(QUEUE_SIZE));
    }

    public void exec(Runnable task) {
        executor.execute(task);
    }

    @PreDestroy
    private void destroy() {
        if (executor != null) {
            try {
                executor.shutdown();
                logger.info("Shutdown GlobalExecService.");
            } catch (Throwable e) {
                logger.info("Shutdown GlobalExecService failed.", e);
            }
        }
    }
}
