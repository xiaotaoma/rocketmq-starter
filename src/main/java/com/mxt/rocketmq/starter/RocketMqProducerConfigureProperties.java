package com.mxt.rocketmq.starter;

import org.springframework.core.env.Environment;

public class RocketMqProducerConfigureProperties {

    private String producerGroupName = "producerGroupName";
    private String producerInstanceName = "producerInstanceName";
    private String nameSrv = "192.168.0.33:9876";

    public RocketMqProducerConfigureProperties(Environment environment) {
        if (environment != null) {
            if (environment.getProperty(RocketConfigConstant.NAME_SRV) != null) {
                this.nameSrv = environment.getProperty(RocketConfigConstant.NAME_SRV);
            }
            if (environment.getProperty(RocketConfigConstant.PRODUCER_GROUP_NAME) != null) {
                this.producerGroupName = environment.getProperty(RocketConfigConstant.PRODUCER_GROUP_NAME);
            }
            if (environment.getProperty(RocketConfigConstant.PRODUCER_INSTANCE_NAME) != null) {
                this.producerInstanceName = environment.getProperty(RocketConfigConstant.PRODUCER_INSTANCE_NAME);
            }
        }
    }

    public String getProducerGroupName() {
        return producerGroupName;
    }

    public void setProducerGroupName(String producerGroupName) {
        this.producerGroupName = producerGroupName;
    }

    public String getProducerInstanceName() {
        return producerInstanceName;
    }

    public void setProducerInstanceName(String producerInstanceName) {
        this.producerInstanceName = producerInstanceName;
    }

    public String getNameSrv() {
        return nameSrv;
    }

    public void setNameSrv(String nameSrv) {
        this.nameSrv = nameSrv;
    }
}
