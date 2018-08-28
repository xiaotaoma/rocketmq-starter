package com.mxt.rocketmq.starter;

/**
 * 配置文件配置累
 */
public enum RocketmqConfigEnum {
    nameSrv("spring.rocketmq.zkzn.namesrv"),

    consumerName("spring.rocketmq.zkzn.consumer.name"),
    consumeMessageBatchMaxSize("spring.rocketmq.zkzn.consumer.batchMaxSize"),
    consumeFromWhere("spring.rocketmq.zkzn.consumer.fromWhere"),
    consumeTopic("spring.rocketmq.zkzn.consumer.topic"),
    consumeMessageModel("spring.rocketmq.zkzn.consumer.messageModel"),
    consumerGroup("spring.rocketmq.zkzn.consumer.group");
























    private String value;

    RocketmqConfigEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
