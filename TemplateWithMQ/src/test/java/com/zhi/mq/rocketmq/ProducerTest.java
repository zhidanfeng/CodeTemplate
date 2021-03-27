package com.zhi.mq.rocketmq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;

import javax.annotation.Resource;

public class ProducerTest {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void send() {
        //rocketMQTemplate.syncSend("");
    }
}
