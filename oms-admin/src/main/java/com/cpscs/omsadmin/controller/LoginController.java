package com.cpscs.omsadmin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpscs.common.annotation.Log;
import com.cpscs.common.context.FilterContextHandler;
import com.cpscs.common.dto.LoginDTO;
import com.cpscs.common.dto.UserToken;
import com.cpscs.common.utils.JwtUtils;
import com.cpscs.common.utils.R;
import com.cpscs.omsadmin.domain.UserDO;
import com.cpscs.omsadmin.service.MenuService;
import com.cpscs.omsadmin.service.TokenService;
import com.cpscs.omsadmin.service.UserService;
import com.cpscs.omsadmin.utils.MD5Utils;

/**
 * @author bootdo 1992lcg@163.com
 * @version V1.0
 */
@RequestMapping()
@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    MenuService menuService;

    @Log("登录")
    @PostMapping("/login")
    R login(@Valid @RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) {
        String username = loginDTO.getUsername().trim();
        String password = loginDTO.getPwd().trim();
        System.out.println(username+":"+password);
        password = MD5Utils.encrypt(username, password);
        Map<String, Object> param = new HashMap<>();
        param.put("username", username);
        List<UserDO> userDOs = userService.list(param);
        if(userDOs.size()<1){
            return R.error("用户或密码错误");
        }
        UserDO userDO = userDOs.get(0);
        if (null == userDO || !userDO.getPassword().equals(password)) {
            return R.error("用户或密码错误");
        }
        UserToken userToken = new UserToken(userDO.getUsername(), userDO.getUserId().toString(), userDO.getName());
        String token="";
        try {
            token = JwtUtils.generateToken(userToken, 2*60*60*1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //首先清除用户缓存权限
        menuService.clearCache(userDO.getUserId());
        // String token = tokenService.createToken(userDO.getUserId());
        return R.ok("登录成功")
                .put("token", token).put("user",userDO)
                .put("perms",menuService.PermsByUserId(userDO.getUserId()))
                .put("router",menuService.RouterDTOsByUserId(userDO.getUserId()));
    }


    @RequestMapping("/logout")
    R logout(HttpServletRequest request, HttpServletResponse response) {
        menuService.clearCache(Long.parseLong(FilterContextHandler.getUserID()));
        return R.ok();
    }


}
