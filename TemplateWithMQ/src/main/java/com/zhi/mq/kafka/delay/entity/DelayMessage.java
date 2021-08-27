package com.zhi.mq.kafka.delay.entity;

import lombok.Data;

@Data
public class DelayMessage {
    private String topic;
    private String group;
    private String msg;
    private Long delayTime;
}
