package top.ccxh.product.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface StreamOutput {
    String KEY = "myMessage";

    @Output(KEY)
    MessageChannel output();
}
