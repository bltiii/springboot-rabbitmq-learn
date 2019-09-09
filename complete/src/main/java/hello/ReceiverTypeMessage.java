package hello;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.util.Map;

@Component

public class ReceiverTypeMessage {
    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(value = Application.topicExchangeName, type = ExchangeTypes.TOPIC, autoDelete = "true"),
                    value = @Queue(value = "spring-boot-message", autoDelete = "true"),
                    key = "foo.bar.multi"
            )
    )
    public void receiveMessage(@Headers Map<String, Object> headers, @Payload Message message) {

        String contentType = (message.getMessageProperties() != null) ? message.getMessageProperties().getContentType() : null;
        if (MessageProperties.CONTENT_TYPE_SERIALIZED_OBJECT.equals(contentType)) {
            MessageWIthId messageWIthId = (MessageWIthId) SerializationUtils.deserialize(message.getBody());
            System.out.println("Received <" + messageWIthId.toString() + "> type: " + messageWIthId.getClass() + "headers = [" + headers + "]");
        }
        if (MessageProperties.CONTENT_TYPE_TEXT_PLAIN.equals(contentType)
                || MessageProperties.CONTENT_TYPE_JSON.equals(contentType)
                || MessageProperties.CONTENT_TYPE_JSON_ALT.equals(contentType)
                || MessageProperties.CONTENT_TYPE_XML.equals(contentType)) {
            String msg = new String(message.getBody());
            System.out.println("Received <" + msg + "> type: " + msg.getClass() + " headers = [" + headers + "]");
        }

        System.out.println(this.toString());
    }


}