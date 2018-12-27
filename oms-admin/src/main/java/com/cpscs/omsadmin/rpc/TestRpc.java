package com.cpscs.omsadmin.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.cpscs.common.intercepter.FeignIntercepter;

@FeignClient(name = "course", configuration = FeignIntercepter.class)
public interface TestRpc {
    @GetMapping("/test/testRpc/test")
    String test();
}
