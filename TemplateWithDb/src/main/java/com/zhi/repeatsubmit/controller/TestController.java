package com.zhi.repeatsubmit.controller;

import cn.hutool.core.date.DateUtil;
import com.zhi.core.form.RepeatSubmitForm;
import com.zhi.repeatsubmit.core.NoRepeatSubmit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "11")
@RequestMapping("/repeatsubmit")
public class TestController {
    /**
     * 防止重复提交
     * @param form
     * @return
     */
    @NoRepeatSubmit(time = 5000)
    @PostMapping("/test")
    public String repeatSubmit(@RequestBody RepeatSubmitForm form) {
        System.out.println("添加记录");
        return "";
    }
}
