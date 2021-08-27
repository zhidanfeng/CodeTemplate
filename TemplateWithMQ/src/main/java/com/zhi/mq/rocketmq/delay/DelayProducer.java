package com.zhi.mq.rocketmq.delay;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rocketmq/delay")
public class DelayProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/send")
    public void send(@RequestParam(value = "msg") String msg, @RequestParam(value = "delayTime") Integer delayTime) {
        String topic = "test_topic";
        String tag = "test_tag";
        Message<String> sendMsg = MessageBuilder.withPayload("Hello").setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE).build();
        rocketMQTemplate.syncSend(topic + ":" + tag, sendMsg, 2000, 2);
    }
}
