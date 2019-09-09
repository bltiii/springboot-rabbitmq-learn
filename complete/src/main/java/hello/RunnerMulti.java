package hello;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RunnerMulti implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;

    public RunnerMulti(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(Application.topicExchangeName, "foo.bar.multi", "Hello from RabbitMQ Multi!");
        rabbitTemplate.convertAndSend(Application.topicExchangeName, "foo.bar.multi", new MessageWIthId("Hello from RabbitMQ Multi, id 1!", 1));

    }

}