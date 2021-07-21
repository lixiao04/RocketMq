package com.example.demo;

import com.example.demo.rocketmq.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class RocketDemoApplicationTests {
    @Autowired
    private Producer producer;
    @Value(value = "${rocketmq.topic}")
    private  String topic;
    @Value(value = "${rocketmq.tag}")
    private  String tag;
    @Test
    public void testSend(){
        try {
            String content="testSend";
            Message message=new Message(topic,tag,content.getBytes());
            SendResult testlix = producer.getProducer().send(message);
            System.out.println(testlix);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
