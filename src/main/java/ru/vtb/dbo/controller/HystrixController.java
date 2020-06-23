package ru.vtb.dbo.controller;

import com.netflix.hystrix.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.vtb.dbo.FakeFeignClient;
import ru.vtb.dbo.LambdaHystrixCommand;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("hystrix")
public class HystrixController {

    private final FakeFeignClient hystrixFakeFeignClient;
    private final RestTemplate wireMockRestTemplate;

    @Value("${delayInSecs:2}")
    private int delayInSecs;
    @Value("${remoteUrl}")
    private String remoteUrl;

    @Autowired
    public HystrixController(FakeFeignClient hystrixFakeFeignClient, RestTemplate wireMockRestTemplate) {
        this.hystrixFakeFeignClient = hystrixFakeFeignClient;
        this.wireMockRestTemplate = wireMockRestTemplate;
    }

    @GetMapping("/feign")
    public String feignInvocation() {
        log.info("feignInvocation() was invoked...!");

        return hystrixFakeFeignClient.getData();
    }

    @GetMapping("/rest-template")
    public String restTemplateInvocation() {
        log.info("restTemplateInvocation() was invoked...!");

        HystrixCommand<String> command = new LambdaHystrixCommand<>(
                "hystrix-rest-template-localhost",
                "fallback default value",
                wireMockDataSupplier());

        return command.execute();
    }

    private Supplier<String> wireMockDataSupplier() {
        return () -> wireMockRestTemplate.getForEntity(remoteUrl + "/data", String.class).getBody();
    }

    private Supplier<String> mockData() {
        return () -> {
            try {
                TimeUnit.SECONDS.sleep(delayInSecs);
                return "ok!";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "error";
        };
    }
}
