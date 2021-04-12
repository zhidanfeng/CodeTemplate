package com.zhi.mq.rocketmq;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import javax.annotation.Resource;

public class ProducerTest {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void send() {
        String topic = "test_topic";
        String tag = "test_tag";
        SendResult sendResult = null;
        try {
            sendResult = rocketMQTemplate.syncSend(topic + ":" + tag, MessageBuilder.withPayload("Hello")
                    .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE).build());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(sendResult);
    }
}
