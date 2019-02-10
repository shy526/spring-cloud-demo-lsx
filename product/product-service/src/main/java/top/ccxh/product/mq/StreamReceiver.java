package top.ccxh.product.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableBinding({StreamOutput.class})
public class StreamReceiver {

    @StreamListener(StreamOutput.KEY)
    public void receiver(Object message){
        log.error("test_stream:"+message);
    }
}
