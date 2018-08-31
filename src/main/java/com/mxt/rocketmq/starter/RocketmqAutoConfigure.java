package com.mxt.rocketmq.starter;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RocketmqAutoConfigure {

    @Autowired
    private Environment environment;

    @Bean
    @ConditionalOnMissingBean(value = {MQConsumer.class, DefaultMQPushConsumer.class, DefaultMQPullConsumer.class})
    @ConditionalOnProperty(value = RocketConfigConstant.CONSUMER_NAME)
    public MQConsumer consumer(RocketMqConsumerConfigureProperties consumerConfigureProperties, MessageListener messageListener) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerConfigureProperties.getConsumerGroup());
        consumer.setNamesrvAddr(consumerConfigureProperties.getNameSrv());
        consumer.setInstanceName(consumerConfigureProperties.getConsumerName());
        consumer.subscribe(consumerConfigureProperties.getConsumerTopic(), consumerConfigureProperties.getConsumerSubExpression());
        consumer.setMessageListener(messageListener);

        int consumeMessageBatchMaxSize = consumerConfigureProperties.getConsumerMessageBatchMaxSize();
        if (consumeMessageBatchMaxSize > 0) {
            consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        }
        ConsumeFromWhere consumeFromWhere = consumerConfigureProperties.getConsumerFromWhere();
        if (consumeFromWhere != null) {
            consumer.setConsumeFromWhere(consumeFromWhere);
        }
        MessageModel messageModel = consumerConfigureProperties.getConsumeMessageModel();
        if (messageModel != null) {
            consumer.setMessageModel(messageModel);
        }

        consumer.start();
        return consumer;
    }

    @Bean
    @ConditionalOnMissingBean(value = RocketMqConsumerConfigureProperties.class)
    public RocketMqConsumerConfigureProperties rocketMqConsumerConfigureProperties() {
        return new RocketMqConsumerConfigureProperties(environment);
    }

    @Bean
    @ConditionalOnMissingBean(value = RocketMqProducerConfigureProperties.class)
    public RocketMqProducerConfigureProperties rocketMqProducerConfigureProperties() {
        return new RocketMqProducerConfigureProperties(environment);
    }

    @Bean
    @ConditionalOnMissingBean(value = {MQProducer.class, DefaultMQProducer.class})
    @ConditionalOnProperty(value = RocketConfigConstant.PRODUCER_INSTANCE_NAME)
    public MQProducer producer(RocketMqProducerConfigureProperties producerConfigureProperties) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer(producerConfigureProperties.getProducerGroupName());
        producer.setNamesrvAddr(producerConfigureProperties.getNameSrv());
        producer.setInstanceName(producerConfigureProperties.getProducerInstanceName());
        producer.start();
        return producer;
    }
}
