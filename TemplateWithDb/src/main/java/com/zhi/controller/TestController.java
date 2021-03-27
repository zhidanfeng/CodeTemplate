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
        if (this.isRepeatRequest(form, 1L, "repeatSubmit", "time")) {
            System.out.println("重复提交");
        } else {
            System.out.println("第一次提交");
        }
        return "";
    }

    /**
     * 判断当前用户下的同一方法中的参数是否存在重复请求
     * @param requestParam 请求参数
     * @param userId 当前用户ID
     * @param method 方法名称
     * @param excludeKey 需要排除的字段。有的请求参数可能包含时间字段，值会不同，从而影响判断，需要排除
     * @return true:重复提交
     */
    private boolean isRepeatRequest(Object requestParam, Long userId, String method, String... excludeKey) {
        String requestParamJson = JSON.toJSONString(requestParam);
        System.out.println(requestParamJson);
        String requestParamMd5 = RepeatSubmitUtil.digestParamMD5(requestParamJson, excludeKey);
        System.out.println(requestParamMd5);

        String key = "repeat:U=" + userId + "M=" + method + "P=" + requestParamMd5;
        long expireTime = 5000;// 5000毫秒过期，5000ms内的重复请求会认为重复
        long expireAt = System.currentTimeMillis() + expireTime;
        String val = "expireAt@" + expireAt;
        // key设置失败则代表已经存在，说明是重复请求
        return !this.redisUtil.setNx(key, val, expireTime);
    }
}
