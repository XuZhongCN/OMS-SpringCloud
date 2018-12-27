package com.cpscs.omszuul.prc.admin;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.cpscs.common.dto.MenuDTO;
import com.cpscs.common.intercepter.FeignIntercepter;

import feign.Headers;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-admin", configuration = FeignIntercepter.class)
public interface MenuService {
    @GetMapping("/menu/userMenus")
    List<MenuDTO> userMenus();
}
