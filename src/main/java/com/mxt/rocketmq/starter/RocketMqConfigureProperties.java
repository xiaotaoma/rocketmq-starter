package com.mxt.rocketmq.starter;

import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class RocketMqConfigureProperties {

    private String nameSrv = "localhost";
    private String consumerName = "consumerInstance";
    private int consumeMessageBatchMaxSize = 10;
    private ConsumeFromWhere consumeFromWhere = ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET;
    private String consumeTopic = "topic";
    /**
     * 消息模式
     * CLUSTERING   集群模式    每个消息被一个消费者消费不在被其他消费者消费
     * BROADCASTING     广播模式    每个消息会发送到每个消费者
     */
    private MessageModel consumeMessageModel = MessageModel.CLUSTERING;

    private String consumerGroup = "consumerGroup";

    @Autowired
    private Environment environment;

    public RocketMqConfigureProperties() {
        if (environment != null) {
            if (environment.getProperty(RocketmqConfigEnum.nameSrv.getValue()) != null) {
                this.nameSrv = environment.getProperty(RocketmqConfigEnum.nameSrv.getValue());
            }
            if (environment.getProperty(RocketmqConfigEnum.consumerName.getValue()) != null) {
                this.consumerName = environment.getProperty(RocketmqConfigEnum.consumerName.getValue());
            }
            if (environment.getProperty(RocketmqConfigEnum.consumerGroup.getValue()) != null) {
                this.consumerGroup = environment.getProperty(RocketmqConfigEnum.consumerGroup.getValue());
            }
            if (environment.getProperty(RocketmqConfigEnum.consumeMessageBatchMaxSize.getValue()) != null) {
                this.consumeMessageBatchMaxSize = Integer.parseInt(environment.getProperty(RocketmqConfigEnum.consumeMessageBatchMaxSize.getValue()));
            }
            if (environment.getProperty(RocketmqConfigEnum.consumeTopic.getValue()) != null) {
                this.consumeTopic = environment.getProperty(RocketmqConfigEnum.consumeTopic.getValue());
            }
            if (environment.getProperty(RocketmqConfigEnum.consumeMessageModel.getValue()) != null) {
                this.consumeMessageModel = MessageModel.valueOf(environment.getProperty(RocketmqConfigEnum.consumeMessageModel.getValue().trim().toUpperCase()));
            }
            if (environment.getProperty(RocketmqConfigEnum.consumeFromWhere.getValue()) != null) {
                this.consumeFromWhere = ConsumeFromWhere.valueOf(environment.getProperty(RocketmqConfigEnum.consumeFromWhere.getValue().trim().toUpperCase()));
            }
        }
    }


    public String getNameSrv() {
        return nameSrv;
    }

    public void setNameSrv(String nameSrv) {
        this.nameSrv = nameSrv;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public int getConsumeMessageBatchMaxSize() {
        return consumeMessageBatchMaxSize;
    }

    public void setConsumeMessageBatchMaxSize(int consumeMessageBatchMaxSize) {
        this.consumeMessageBatchMaxSize = consumeMessageBatchMaxSize;
    }

    public ConsumeFromWhere getConsumeFromWhere() {
        return consumeFromWhere;
    }

    public void setConsumeFromWhere(ConsumeFromWhere consumeFromWhere) {
        this.consumeFromWhere = consumeFromWhere;
    }

    public String getConsumeTopic() {
        return consumeTopic;
    }

    public void setConsumeTopic(String consumeTopic) {
        this.consumeTopic = consumeTopic;
    }

    public MessageModel getConsumeMessageModel() {
        return consumeMessageModel;
    }

    public void setConsumeMessageModel(MessageModel consumeMessageModel) {
        this.consumeMessageModel = consumeMessageModel;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
