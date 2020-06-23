package ru.vtb.dbo.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.dbo.FakeFeignClient;

@Slf4j
@RefreshScope
@AllArgsConstructor
@RestController
@RequestMapping("spring-cloud")
public class SpringCloudController {

    private final FakeFeignClient fakeFeignClient;

    @GetMapping("/feign")
    public String feignInvocation() {
        log.info("spring-cloud feignInvocation() was invoked...!");

        return fakeFeignClient.getData();
    }
}
