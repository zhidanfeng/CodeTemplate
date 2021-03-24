package com.zhi.service.impl;

import com.zhi.dao.TestDao;
import com.zhi.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestDao testDao;

    @Override
    public String sayHi(String words) {
        return "you say：" + this.testDao.sayHi(words);
    }
}
