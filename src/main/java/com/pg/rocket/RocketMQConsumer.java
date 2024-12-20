package com.pg.rocket;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RocketMQConsumer {
    private static final Logger log = LoggerFactory.getLogger(RocketMQConsumer.class);

    public static void main(String[] args) {
        try{
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
            consumer.setNamesrvAddr("127.0.0.1:9876");
            consumer.setConsumeThreadMax(21);
            consumer.setConsumerGroup("yutak-dev");
            consumer.setTraceTopic("yutak-dev");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    if(list == null || list.size() == 0){
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                    MessageExt msg = list.get(0);
                    try{
                        String body = new String(msg.getBody());
                        System.out.println("current time:"+System.currentTimeMillis()+"message:"+body);
                        int a = 1 / 0;
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }catch (Exception e){
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
            });
            System.out.println("yutak dev  consumer start");
            consumer.start();

        }catch(Exception e){
            log.error("consumer start error", e);
        }
    }
}
