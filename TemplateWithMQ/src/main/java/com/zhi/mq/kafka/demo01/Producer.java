package com.zhi.mq.kafka.demo01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class Producer {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Autowired
    private MyProducterListener myProducterListener;

    @GetMapping("/send")
    public void send(@RequestParam(value = "msg") String msg) {
        kafkaTemplate.setProducerListener(myProducterListener);
        kafkaTemplate.send("kafka_demo01", msg);
    }
}
