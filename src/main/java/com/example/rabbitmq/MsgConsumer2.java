/**
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: sanrenxing </p>
 */
package com.example.rabbitmq;

 
import java.nio.charset.StandardCharsets;

import lombok.extern.log4j.Log4j2;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@Component
@Log4j2
public class MsgConsumer2 {
    
     
    //********************************************
    @RabbitListener(
            bindings =
            {
                    @QueueBinding(
                            value = @Queue(value = RabbitConfig2.DELAY_QUEUE, durable = "true"),
                            exchange = @Exchange(value = RabbitConfig2.DELAY_EXCHANGE, type = "direct",
                            arguments=@Argument(name="x-delayed-type",value="direct"),delayed="true"),
                            key = RabbitConfig2.DELAY_KEY)
            })
     
 
     
    public void processTopicMsg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        log.info("received Topic message : " + msg);
    }
}
