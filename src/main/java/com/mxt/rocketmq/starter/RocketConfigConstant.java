package com.mxt.rocketmq.starter;

import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 常量
 */
public class RocketConfigConstant {

    public static final String NAME_SRV = "spring.rocketmq.namesrv";
    public static final String CONSUMER_GROUP = "spring.rocketmq.consumer.group";
    public static final String CONSUMER_NAME = "spring.rocketmq.consumer.name";
    public static final String CONSUMER_TOPIC = "spring.rocketmq.consumer.topic";
    public static final String CONSUMER_SUB_EXPRESSION = "spring.rocketmq.consumer.sub-expression";

    public static final String CONSUMER_MESSAGE_BATCH_MAX_SIZE = "spring.rocketmq.consumer.batchMaxSize";
    public static final String CONSUMER_MESSAGE_MODEL = "spring.rocketmq.consumer.messageModel";

    public static final String PRODUCER_GROUP_NAME = "spring.rocketmq.producer.group";
    public static final String PRODUCER_INSTANCE_NAME = "spring.rocketmq.producer.name";


    public static final String NAME_SRV_DEFAULT = "127.0.0.1:9876";
    public static String CONSUMER_NAME_DEFAULT = "";
    public static final String CONSUMER_TOPIC_DEFAULT = "";
    public static final String CONSUMER_GROUP_DEFAULT = "";
    public static final MessageModel CONSUME_MESSAGE_MODEL_DEFAULT = MessageModel.CLUSTERING;

    static {
        try {
            InetAddress address = InetAddress.getLocalHost();
            String hostAddress = address.getHostAddress();
            CONSUMER_NAME_DEFAULT = hostAddress;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
