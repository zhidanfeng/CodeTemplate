package com.zhi.service.impl;

import com.zhi.dao.TestDao;
import com.zhi.service.TestService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestDao testDao;

    @Override
    public String sayHi(String words) {
        this.a();
        this.b();
        this.c();
        this.d();
        return "name is：zhi";
    }

    @SneakyThrows
    private void a() {
        System.out.println("a method execute...");
    }

    @SneakyThrows
    private void b() {
        TimeUnit.SECONDS.sleep(2);
    }

    @SneakyThrows
    private void c() {
        TimeUnit.SECONDS.sleep(20);
        this.d();
    }

    @SneakyThrows
    private void d() {
        System.out.println("d method execute...");
    }
}
