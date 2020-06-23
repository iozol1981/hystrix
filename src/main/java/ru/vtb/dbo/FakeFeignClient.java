/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2020 VTB Group. All rights reserved.
 */

package ru.vtb.dbo;

import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * FakeFeignClient.
 *
 * @author Igor_Ozol
 */
@FeignClient(name = "fake-service", url = "${remoteUrl}", fallback = FakeFeignFallback.class)
public interface FakeFeignClient {

    @RequestLine("GET /data")
    @RequestMapping(method = RequestMethod.GET, value = "/data")
    String getData();
}
