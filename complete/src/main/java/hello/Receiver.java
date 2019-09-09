package hello;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component

public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(value = Application.topicExchangeName, type = ExchangeTypes.TOPIC, autoDelete = "true"),
                    value = @Queue(value = "spring-boot", autoDelete = "true"),
                    key = "foo.bar.baz"
            )
    )
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        System.out.println(this.toString());
        latch.countDown();
    }
    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(value = Application.topicExchangeName, type = ExchangeTypes.TOPIC, autoDelete = "true"),
                    value = @Queue(value = "spring-boot-2", autoDelete = "true"),
                    key = "foo.bar.pig"
            )
    )
    @RabbitListener(queues = "spring-boot")
    public void receiveMessage2(String message) {
        System.out.println("Received <" + message + "> 2");
        System.out.println(this.toString());
        latch.countDown();
    }
    public CountDownLatch getLatch() {
        return latch;
    }

}