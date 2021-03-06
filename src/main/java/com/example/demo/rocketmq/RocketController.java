package com.example.demo.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.LogFactory;
import org.apache.rocketmq.client.QueryResult;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
public class RocketController {
    @Autowired
    private  Producer producer;
    @Value(value = "${rocketmq.topic}")
    private  String topic;
    @Value(value = "${rocketmq.tag}")
    private  String tag;
    @RequestMapping(value = "sendMessage")
    @ResponseBody
    public String sendMessage(@RequestParam(value = "msgContent")String msgContent) {
        Message msg = new Message(topic, tag, msgContent.getBytes());
        msg.setKeys(msgContent);
        SendResult sendResult = null;
        try {
            sendResult = producer.getProducer().send(msg);
        } catch (MQClientException e) {
            log.error(e.getMessage() + String.valueOf(sendResult));
        } catch (RemotingException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage(),e);
        } catch (MQBrokerException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage(),e);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage(),e);
        }
        // ????????????????????????????????????
        if (sendResult == null || sendResult.getSendStatus() != SendStatus.SEND_OK) {
            // TODO
            log.error("?????????????????????");
            return "false";
        }else {
            log.info("?????????????????????");
            return "true";
        }
    }

}
