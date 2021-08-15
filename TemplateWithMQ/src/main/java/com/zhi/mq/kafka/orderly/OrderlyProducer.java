package com.zhi.mq.kafka.orderly;

import com.zhi.mq.kafka.demo01.MyProducterListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderly")
public class OrderlyProducer {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Autowired
    private MyProducterListener myProducterListener;

    @GetMapping("/send")
    public void send(@RequestParam(value = "msg") String msg, @RequestParam(value = "orderly") boolean orderly) {
        kafkaTemplate.setProducerListener(myProducterListener);
        System.out.println(orderly ? "有序消息" : "乱序消息");
        for (int i = 0; i < 4; i++) {
            // 要保证消息的有序性，需要保证只有1个消费者组，并且只能将消息发往同一个分区
            kafkaTemplate.send("kafka_demo01", orderly ? 0 : i % 3, String.valueOf(i), msg + i);
        }
    }
}
