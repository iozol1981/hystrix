package ru.vtb.dbo;

import lombok.Data;

@Data
public class HystrixDataDto {
    private String isolationStrategy;
    private int semaphoreMaxConcurrentRequests; // SEMAPHORE isolation strategy

    private int threadTimeoutInMilliseconds; // THREAD isolation strategy

    private int threadPoolMaximumSize = 300;
    private int threadPoolCoreSize = 50;
    private boolean threadPoolAllowMaximumSizeToDivergeFromCoreSize = true;


    private boolean rollingPercentileEnabled;

//
//            config.setProperty("hystrix.command.default.execution.isolation.strategy", "SEMAPHORE");
//        config.setProperty("hystrix.command.default.execution.isolation.semaphore.maxConcurrentRequests", "2000");
//
////        config.setProperty("hystrix.command.default.execution.isolation.strategy", "THREAD");
////        config.setProperty("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", "1000");
//
////        config.setProperty("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", thread_timeoutInMilliseconds);
////        config.setProperty("hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds", circuitBreaker_sleepWindowInMilliseconds);
//
//        config.setProperty("hystrix.command.default.metrics.rollingPercentile.enabled", rollingPercentile_enabled);
//        config.setProperty("hystrix.command.default.metrics.rollingPercentile.numBuckets", rollingPercentile_numBuckets);
//        config.setProperty("hystrix.command.default.metrics.rollingPercentile.bucketSize", rollingPercentile_bucketSize);
}
