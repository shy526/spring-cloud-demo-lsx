package top.ccxh.orderservice;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class SendMQ extends OrderServiceApplicationTests {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void sen(){
        amqpTemplate.convertAndSend("test","now"+new Date());
        amqpTemplate.convertAndSend("exchange-01","01","01接收的");
        amqpTemplate.convertAndSend("exchange-01","02","02接收的");
    }
}
