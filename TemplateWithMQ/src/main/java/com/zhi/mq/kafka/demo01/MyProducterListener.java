package com.zhi.mq.kafka.demo01;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

@Component
public class MyProducterListener implements ProducerListener {
    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        System.out.println(producerRecord.value() + "send success...");
    }

    @Override
    public void onError(ProducerRecord producerRecord, Exception exception) {
        System.out.println("send error...");
    }
}
