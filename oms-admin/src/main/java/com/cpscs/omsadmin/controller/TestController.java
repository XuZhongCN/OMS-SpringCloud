package com.cpscs.omsadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpscs.omsadmin.rpc.TestRpc;

@RestController
@RequestMapping
public class TestController {
    @Autowired
    TestRpc testRpc;

    @GetMapping("test")
    String test() {
        return testRpc.test();
    }
    @GetMapping("test1")
    String test1() {
        return "1111";
    }
}
