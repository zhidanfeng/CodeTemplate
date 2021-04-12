package com.zhi.datadesensitize.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DataDesensitizeController {
    @GetMapping("/sayHi")
    public String sayHi(@RequestParam(value = "words") String words) {
        return "";
    }
}
