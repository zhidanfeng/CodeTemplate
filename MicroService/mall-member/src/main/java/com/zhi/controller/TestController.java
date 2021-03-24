package com.zhi.controller;

import com.zhi.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private TestService testService;

    @GetMapping("/sayHi")
    public String sayHi(@RequestParam(value = "words") String words) {
        return this.testService.sayHi(words);
    }
}
