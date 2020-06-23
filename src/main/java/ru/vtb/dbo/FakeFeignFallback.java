package ru.vtb.dbo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FakeFeignFallback implements FakeFeignClient {
    @Override
    public String getData() {
        log.warn("****************************** FakeFeignFallback was invoked! *****************************************");
        return "fallback";
    }
}
