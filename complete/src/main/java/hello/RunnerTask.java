package hello;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RunnerTask implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    //private final Receiver receiver;
    private TaskExecutor taskExecutor;

    public RunnerTask(RabbitTemplate rabbitTemplate, TaskExecutor taskExecutor) {
        //this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
        this.taskExecutor = taskExecutor;
    }
    private class MessagePrinterTask implements Runnable {
        private final RabbitTemplate rabbitTemplate;
        private String message;

        public MessagePrinterTask(String message, RabbitTemplate rabbitTemplate) {
           this.message= message;
           this.rabbitTemplate = rabbitTemplate;
        }

        public void run() {
            System.out.println("Sending message...");
            rabbitTemplate.convertAndSend(Application.topicExchangeName, "foo.bar.pig", "Hello from RabbitMQ " + message);
        }

    }
    @Override
    public void run(String... args) throws Exception {

        for(int i = 0; i < 1000; i++) {
            taskExecutor.execute(new MessagePrinterTask("id: " + i, rabbitTemplate));
        }
    }

}