package com.cpscs.omszuul.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpscs.common.constants.CommonConstants;
import com.cpscs.common.context.FilterContextHandler;
import com.cpscs.common.dto.MenuDTO;
import com.cpscs.omszuul.prc.admin.MenuService;

/**
 * @version V1.0
 */
@RestController
public class LoginController {
    @Autowired
    MenuService menuService;
    @GetMapping({"/test"})
    List<MenuDTO> login(HttpServletRequest request)  {
        FilterContextHandler.setToken(request.getHeader(CommonConstants.CONTEXT_TOKEN));
        return menuService.userMenus();
    }
}
