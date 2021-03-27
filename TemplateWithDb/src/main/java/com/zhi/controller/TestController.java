package com.zhi.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.zhi.core.form.RepeatSubmitForm;
import com.zhi.service.TestService;
import com.zhi.util.RedisUtil;
import com.zhi.util.RepeatSubmitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/sayHi")
    public String sayHi(@RequestParam(value = "words") String words) {
        String json_1 = String.format("{\"companyId\": 70288883758534656,\"size\": 20,\"time\":111}");
        String md5_1 = RepeatSubmitUtil.digestParamMD5(json_1, "time");
        System.out.println(md5_1);

        String json_2 = String.format("{\"companyId\": 70288883758534656,\"size\": 20,\"time\":222}");
        String md5_2 = RepeatSubmitUtil.digestParamMD5(json_2, "time");
        System.out.println(md5_2);

        return this.testService.sayHi(words);
    }

    /**
     * 防止重复提交
     * @param form
     * @return
     */
    @PostMapping("/repeatSubmit")
    public String repeatSubmit(@RequestBody RepeatSubmitForm form) {
        form.setTime(DateUtil.current());
        String json_1 = JSON.toJSONString(form);
        System.out.println(json_1);
        String md5_1 = RepeatSubmitUtil.digestParamMD5(json_1, "time");
        System.out.println(md5_1);

        String key = "repeat:U=" + 1L + "M=" + "method" + "P=" + md5_1;
        long expireTime = 5000;// 1000毫秒过期，1000ms内的重复请求会认为重复
        long expireAt = System.currentTimeMillis() + expireTime;
        String val = "expireAt@" + expireAt;
        if (this.redisUtil.setNx(key, val, expireTime)) {
            System.out.println("第一次提交");
        } else {
            System.out.println("重复提交");
        }
        return md5_1;
    }
}
