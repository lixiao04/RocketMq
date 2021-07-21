package com.example.demo.rocketmq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
@Component
@Slf4j
public class Producer {
    @Value(value = "${rocketmq.namesrv}")
    private  String nameservAddr;
    @Value(value = "${rocketmq.group}")
    private  String group;
    @Value(value = "${rocketmq.topic}")
    private  String topic;
    @Value(value = "${rocketmq.tag}")
    private  String tag;
    private  DefaultMQProducer producer = null;
    @PostConstruct
    public  void Producer() throws MQClientException {
        producer = new DefaultMQProducer(group);
        producer.setNamesrvAddr(nameservAddr);
        producer.setInstanceName("producer");
        producer.setRetryTimesWhenSendFailed(3);
        producer.start();
        log.info("生产者启动成功！");
    }

    public DefaultMQProducer getProducer() {
        return producer;
    }
}
