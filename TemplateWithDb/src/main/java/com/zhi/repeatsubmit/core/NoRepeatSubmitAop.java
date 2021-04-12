package com.zhi.repeatsubmit.core;

import com.alibaba.fastjson.JSON;
import com.zhi.util.RedisUtil;
import com.zhi.util.RepeatSubmitUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 防止重复提交的思路
 * 1、利用token。
 * 思路：前端每次请求时将唯一token传给后端，后端将该token放入redis中，如果set成功，说明在时间范围内该请求时第一次提交
 * 缺点：每次请求，比如add、update之前，首先得从后端获取该唯一标识token。这种方式得改造所有涉及到重复提交的接口，工作量大
 *
 * 2、将请求参数+方法名+请求用户作为唯一请求标识
 * 思路：将请求参数+方法名+请求用户使用MD5加密，获取MD5摘要，将该摘要set到redis中，并使用原子方式设置过期时间。
 * 如果过期时间内set失败，说明是重复请求，则将该请求丢弃。这种方式需要注意的是需要排除掉可能影响MD5摘要的值，比如时间
 *
 */
@Aspect
@Component
public class NoRepeatSubmitAop {

    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("@annotation(com.zhi.repeatsubmit.core.NoRepeatSubmit)")
    public void serviceNoRepeat() {
    }

    @Around("serviceNoRepeat()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object[] params = pjp.getArgs();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        NoRepeatSubmit noRepeatSubmit = signature.getMethod().getAnnotation(NoRepeatSubmit.class);

        String requestParamJson = JSON.toJSONString(params[0]);
        //System.out.println(requestParamJson);
        String[] excludeKey = noRepeatSubmit.excludeKey().split(",");
        String requestParamMd5 = RepeatSubmitUtil.digestParamMD5(requestParamJson, excludeKey);
        //System.out.println(requestParamMd5);
        Long userId = 1L;
        String method = pjp.getSignature().getDeclaringTypeName() + "#" + pjp.getSignature().getName();
        String key = "repeat:U=" + userId + "M=" + method + "P=" + requestParamMd5;
        long expireTime = noRepeatSubmit.time();// 5000毫秒过期，5000ms内的重复请求会认为重复
        long expireAt = System.currentTimeMillis() + expireTime;
        String val = "expireAt@" + expireAt;

        if (redisUtil.setNX(key, val, expireTime)) {
            System.out.println("第一次提交");
            return pjp.proceed();
        } else {
            System.out.println("重复提交");
            //throw new Exception("重复提交");
            return "";
        }
    }
}
