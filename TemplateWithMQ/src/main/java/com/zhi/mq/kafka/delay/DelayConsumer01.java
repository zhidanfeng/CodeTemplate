package com.zhi.mq.kafka.delay;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.zhi.mq.kafka.delay.entity.DelayMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class DelayConsumer01 {

    @KafkaListener(id = "DelayConsumer01",
            topics = "kafka_demo01",
            groupId = "kafka_demo01_group",
            containerFactory = "manualImmediateListenerContainerFactory")
    public void receiveListener(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
        List<DelayMessage> delayMessageList = new ArrayList<>();
        for (ConsumerRecord<String, String> record : records) {
            String value = record.value();
            System.out.println("DelayConsumer01收到消息：" + value);
            if (StrUtil.isBlank(value)) {
                continue;
            }
            DelayMessage delayMessage = JSONUtil.toBean(value, DelayMessage.class);
            if (delayMessage == null) {
                throw new IllegalArgumentException("非延时消息...");
            }
            delayMessageList.add(delayMessage);
        }
        HashMap<String, String> map = new HashMap<>(14);
        // 提交offset，否则每次服务重启都会从上一次未提交的offset处开始消费消息
        ack.acknowledge();
    }
}
