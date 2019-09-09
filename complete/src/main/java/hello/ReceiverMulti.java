package hello;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(
        bindings = @QueueBinding(
                exchange = @Exchange(value = Application.topicExchangeName, type = ExchangeTypes.TOPIC, autoDelete = "true"),
                value = @Queue(value = "spring-boot-multi", autoDelete = "true"),
                key = "foo.bar.multi"
        )
)
public class ReceiverMulti {
    @RabbitHandler
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + "> type: String");
        System.out.println(this.toString());
    }
    @RabbitHandler
    public void receiveMessage(MessageWIthId message) {
        System.out.println("Received <" + message + "> type: MessageWIthId");
        System.out.println(this.toString());
    }

}