package com.zhi.mq.rocketmq;

public enum RocketMqEnum {
    a("test_topic", "test_group", "*", ""),
    ;

    private String topic;
    private String group;
    private String tag;
    private String desc;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    RocketMqEnum(String topic, String group, String tag, String desc) {
        this.topic = topic;
        this.group = group;
        this.tag = tag;
        this.desc = desc;
    }
}
