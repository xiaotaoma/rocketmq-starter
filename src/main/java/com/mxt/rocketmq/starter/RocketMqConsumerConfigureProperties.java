package com.mxt.rocketmq.starter;

import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.core.env.Environment;

public class RocketMqConsumerConfigureProperties {

    private String nameSrv = "192.168.0.33:9876";
    private String consumerName = "consumerInstance";
    private ConsumeFromWhere consumerFromWhere = ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET;
    private int consumerMessageBatchMaxSize = 10;
    private String consumerTopic = "topic";
    private String consumerSubExpression = "*";
    /**
     * 消息模式
     * CLUSTERING   集群模式    每个消息被一个消费者消费不在被其他消费者消费
     * BROADCASTING     广播模式    每个消息会发送到每个消费者
     */
    private MessageModel consumeMessageModel = MessageModel.CLUSTERING;

    private String consumerGroup = "consumerGroup";

    public RocketMqConsumerConfigureProperties(Environment environment) {
        if (environment == null) {
            throw new RocketAutoConfigurationException("配置未正确加载");
        }

        String nameSrv = environment.getProperty(RocketConfigConstant.NAME_SRV);
        if (!empty(nameSrv)) {
            this.nameSrv = nameSrv;
        }
        String consumerName = environment.getProperty(RocketConfigConstant.CONSUMER_NAME);
        if (!empty(consumerName)) {
            this.consumerName = consumerName;
        }
        String consumerGroup = environment.getProperty(RocketConfigConstant.CONSUMER_GROUP);
        if (!empty(consumerGroup)) {
            this.consumerGroup = consumerGroup;
        }
        String consumerTopic = environment.getProperty(RocketConfigConstant.CONSUMER_TOPIC);
        if (!empty(consumerTopic)) {
            this.consumerTopic = consumerTopic;
        }
        String subExpression = environment.getProperty(RocketConfigConstant.CONSUMER_SUB_EXPRESSION);
        if (!empty(subExpression)) {
            this.consumerSubExpression = subExpression;
        }

        String messageBatchMaxSize = environment.getProperty(RocketConfigConstant.CONSUMER_MESSAGE_BATCH_MAX_SIZE);
        if (messageBatchMaxSize != null) {
            try {
                this.consumerMessageBatchMaxSize = Integer.parseInt(messageBatchMaxSize);
            }catch (Exception e) {
                throw new RocketAutoConfigurationException(RocketConfigConstant.CONSUMER_MESSAGE_BATCH_MAX_SIZE + " must be a number!");
            }
        }
        String messageModel = environment.getProperty(RocketConfigConstant.CONSUMER_MESSAGE_MODEL);
        if (messageModel != null) {
            try {
                this.consumeMessageModel = MessageModel.valueOf(environment.getProperty(RocketConfigConstant.CONSUMER_MESSAGE_MODEL).trim().toUpperCase());
            }catch (Exception e) {
                throw new RocketAutoConfigurationException(RocketConfigConstant.CONSUMER_MESSAGE_MODEL + "must be " + MessageModel.CLUSTERING + " or" + MessageModel.BROADCASTING + "!");
            }
        }
    }

    private boolean empty(String str) {
        if(str == null || str.length() == 0) {
            return true;
        }
        return false;
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

    public ConsumeFromWhere getConsumerFromWhere() {
        return consumerFromWhere;
    }

    public void setConsumerFromWhere(ConsumeFromWhere consumerFromWhere) {
        this.consumerFromWhere = consumerFromWhere;
    }

    public int getConsumerMessageBatchMaxSize() {
        return consumerMessageBatchMaxSize;
    }

    public void setConsumerMessageBatchMaxSize(int consumerMessageBatchMaxSize) {
        this.consumerMessageBatchMaxSize = consumerMessageBatchMaxSize;
    }

    public String getConsumerTopic() {
        return consumerTopic;
    }

    public void setConsumerTopic(String consumerTopic) {
        this.consumerTopic = consumerTopic;
    }

    public String getConsumerSubExpression() {
        return consumerSubExpression;
    }

    public void setConsumerSubExpression(String consumerSubExpression) {
        this.consumerSubExpression = consumerSubExpression;
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
}
