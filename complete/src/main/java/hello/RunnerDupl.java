package hello;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RunnerDupl implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    //private final Receiver receiver;

    public RunnerDupl(RabbitTemplate rabbitTemplate) {
        //this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(Application.topicExchangeName, "foo.bar.pig", "Hello from RabbitMQ RunnerDupl!");
        //receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        //System.out.println("Counted Down -- RunnerDupl");
    }

}