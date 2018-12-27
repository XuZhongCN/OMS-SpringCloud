package com.cpscs.common.service;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;

import com.cpscs.common.dto.LogDO;
import com.cpscs.common.intercepter.FeignIntercepter;
import com.cpscs.common.utils.R;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-base", configuration = FeignIntercepter.class)
public interface LogRpcService {
    @Async
    @PostMapping("log/save")
    R save(LogDO logDO);
}
