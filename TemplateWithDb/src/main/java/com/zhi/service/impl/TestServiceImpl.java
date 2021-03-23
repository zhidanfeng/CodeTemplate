package com.zhi.service.impl;

import com.zhi.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public String sayHi(String words) {
        return "you say：" + words;
    }
}
