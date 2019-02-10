package top.ccxh.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ccxh.product.mq.StreamOutput;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RefreshScope
public class TestController {
    @Autowired
    @Qualifier(StreamOutput.KEY)
    private MessageChannel messageChannel;
    @Value("${person.name}")
    private String name;
    @Value("${person.age}")

    private Integer age;
    @RequestMapping("test")
    public String test(){
        int i= 1/0;
        return "Test";
    }
    @GetMapping("person")
    @ResponseBody
    public Map<String,Object> getPerson(){
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("age",age);
        stringObjectHashMap.put("name",name);
        return stringObjectHashMap;
    }

    @GetMapping("send/message")
    public void  sendMessage(){
        MessageBuilder<String> stringMessageBuilder = MessageBuilder.withPayload("now:" + new Date());
        messageChannel.send(stringMessageBuilder.build());
    }
}
