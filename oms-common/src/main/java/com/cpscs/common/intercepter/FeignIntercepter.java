package com.cpscs.common.intercepter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cpscs.common.constants.CommonConstants;
import com.cpscs.common.context.FilterContextHandler;

public class FeignIntercepter implements RequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        logger.info("------feign设置token" + Thread.currentThread().getId());
        requestTemplate.header(CommonConstants.CONTEXT_TOKEN, FilterContextHandler.getToken());
    } 
} 