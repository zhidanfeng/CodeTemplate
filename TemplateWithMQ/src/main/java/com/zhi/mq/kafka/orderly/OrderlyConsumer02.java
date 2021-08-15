package com.zhi.mq.kafka.orderly;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderlyConsumer02 {

    @KafkaListener(id = "OrderlyConsumer02",
            topics = "kafka_demo01",
            groupId = "kafka_demo01_group_2",
            containerFactory = "manualImmediateListenerContainerFactory")
    public void receiveListener(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
        for (ConsumerRecord<String, String> record : records) {
            String value = record.value();
            System.out.println("OrderlyConsumer02收到消息：" + value);

            if (value.contains("kafka3")) {
                throw new RuntimeException("处理kafka消息时出现异常");
            }
        }
        // 提交offset，否则每次服务重启都会从上一次未提交的offset处开始消费消息
        ack.acknowledge();
    }
}
