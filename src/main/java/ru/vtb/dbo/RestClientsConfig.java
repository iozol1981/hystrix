package ru.vtb.dbo;

import feign.Feign;
import feign.hystrix.HystrixFeign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientsConfig {

    public static final int READ_TIME_OUT = 5000;
    public static final int CONNECTION_TIME_OUT = 5000;

    @Value("${remoteUrl:http://localhost:8080}")
    private String remoteUrl;

    @Bean
    public RestTemplate getRestTemplate() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setReadTimeout(READ_TIME_OUT);
        requestFactory.setConnectTimeout(CONNECTION_TIME_OUT);
        return new RestTemplate(requestFactory);
    }

    @Bean
    public FakeFeignClient hystrixFakeFeignClient() {
//        https://www.baeldung.com/spring-cloud-openfeign
        return
                HystrixFeign.builder()
//                        .client(new OkHttpClient())
//                        .encoder(new GsonEncoder())
//                        .decoder(new GsonDecoder())
//                        .logger(new Slf4jLogger(FakeFeignClient.class))
//                        .logLevel(Logger.Level.FULL)
                        .target(FakeFeignClient.class, remoteUrl);
    }

    @Bean
    public FakeFeignClient simpleFakeFeignClient() {
        return
                Feign.builder()
                        .target(FakeFeignClient.class, remoteUrl);
    }
}
