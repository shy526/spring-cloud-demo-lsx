package top.ccxh.orderservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReceiverMQ  {


    @RabbitListener(queuesToDeclare = {@Queue("test")})
    //@RabbitListener(queues = {"test"})
    public void  receiver(String message){
        log.error("test队列接收消息:{}",message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("test-01"),
            exchange = @Exchange("exchange-01"),
            key="01"
    ))
    public void receiver1(String message){
        log.error("test-01队列接收消息:{}",message);
    }


    /**
     * 通道绑定
     * 都会自动创建
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("test-02"),
            exchange = @Exchange("exchange-01"),
            key="02"
    ))
    public void receiver2(String message){
        log.error("test-02队列接收消息:{}",message);
    }
}
