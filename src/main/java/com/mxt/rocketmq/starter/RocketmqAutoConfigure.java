package com.mxt.rocketmq.starter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketmqAutoConfigure {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "spring.rocketmq.zkzn.namesrv")
    public DefaultMQPushConsumer consumer(RocketMqConfigureProperties rocketMqConfigureProperties, MessageListener messageListener) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketMqConfigureProperties.getConsumerGroup());
        consumer.setNamesrvAddr(rocketMqConfigureProperties.getNameSrv());
        consumer.setInstanceName(rocketMqConfigureProperties.getConsumerName());
        consumer.setConsumeMessageBatchMaxSize(rocketMqConfigureProperties.getConsumeMessageBatchMaxSize());
        consumer.setConsumeFromWhere(rocketMqConfigureProperties.getConsumeFromWhere());
        consumer.subscribe(rocketMqConfigureProperties.getConsumeTopic(), "*");
        consumer.setMessageModel(rocketMqConfigureProperties.getConsumeMessageModel());
        consumer.setMessageListener(messageListener);
        return consumer;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "spring.rocketmq.zkzn.namesrv")
    public RocketMqConfigureProperties rocketMqConfigureProperties() {
        RocketMqConfigureProperties rocketMqConfigureProperties = new RocketMqConfigureProperties();
        return rocketMqConfigureProperties;
    }

}
