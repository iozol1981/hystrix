package ru.vtb.dbo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.vtb.dbo.FakeFeignClient;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("/")
public class SimpleController {

    private final FakeFeignClient simpleFakeFeignClient;
    private final RestTemplate restTemplate; // ????

    @Value("${remoteUrl}")
    private String remoteUrl;

    @Autowired
    public SimpleController(FakeFeignClient simpleFakeFeignClient, RestTemplate getDataRestTemplate) {
        this.simpleFakeFeignClient = simpleFakeFeignClient;
        this.restTemplate = getDataRestTemplate;
    }

    @GetMapping("/feign")
    public String feignInvocation() {
        log.info("feignInvocation() was invoked...!");

        String result = simpleFakeFeignClient.getData();

        log.info("result = {}", result);
        return result;
    }

    @GetMapping("/rest-template")
    public String restTemplateInvocation() {
        log.info("restTemplateInvocation() was invoked...!");

        RestTemplate restTemplate = new RestTemplate(); // ????
        ResponseEntity<String> response
                = restTemplate.getForEntity(remoteUrl + "/data", String.class);
        return response.getBody();
    }
}
