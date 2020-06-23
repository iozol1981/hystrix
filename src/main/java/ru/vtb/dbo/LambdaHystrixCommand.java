package ru.vtb.dbo;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
public class LambdaHystrixCommand<T> extends HystrixCommand<T> {

    private HystrixCommandGroupKey key;
    private T defaultValue;

    private Supplier<T> supplier;

    public LambdaHystrixCommand(String key, T defaultValue, Supplier<T> supplier) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(key)));
        this.supplier = supplier;
    }

    @Override
    protected T run() throws Exception {
        return supplier.get();
    }

    @Override
    protected T getFallback() {
        log.warn("an error occurred, return defaultValue");
        return defaultValue;
    }
}
