package com.cpscs.omsadmin.utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.cpscs.omsadmin.dao.TokenDao;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author bootdo 1992lcg@163.com
 * @version V1.0
 */
public class TokenUtils {
    @Autowired
    TokenDao tokenDao;
    String getCurrent(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

}
