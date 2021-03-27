package com.zhi.mq.rocketmq.basic;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.support.RocketMQHeaders;

import java.nio.charset.StandardCharsets;

@RocketMQMessageListener(topic = "test_topic", consumerGroup = "test_group", selectorExpression = "*")
public class Consumer implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt messageExt) {
        String msgId = messageExt.getProperties().get(RocketMQHeaders.TRANSACTION_ID);
        String body = new String(messageExt.getBody(), StandardCharsets.UTF_8);
        System.out.println("收到消息：" + body);
    }
}
