package com.pg.rocket;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RocketMQProducer {


    private static final Logger log = LoggerFactory.getLogger(RocketMQProducer.class);

    public static void main(String[] args) {
        try{
            DefaultMQProducer producer = new DefaultMQProducer();
            producer.setRetryTimesWhenSendFailed(2);
            producer.setProducerGroup("yutak-dec-producer");
            producer.setNamesrvAddr("127.0.0.1:9876");
            producer.setTopics(List.of("yutak-dev"));
            log.info("yutak producer start success");
            producer.start();
            producer.send(new Message("yutak-dev","hello world".getBytes()));
        }catch (Exception e){
            log.error("yutak producer start error:{}",e.getMessage());
        }
    }
}
