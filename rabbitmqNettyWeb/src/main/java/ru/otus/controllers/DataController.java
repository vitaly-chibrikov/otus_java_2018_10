package ru.otus.controllers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import ru.otus.domain.MessageForClient;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Controller
public class DataController {
    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    private final static String QUEUE_NAME = "QUEUE_DATA";

    private final Channel channel;
    private final SimpMessagingTemplate template;

    DataController(SimpMessagingTemplate template) throws IOException, TimeoutException {
        this.template = template;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        queueClient();
    }

    private void queueClient() {
        try {
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String data = new String(delivery.getBody(), "UTF-8");
                logger.info("Received '{}'", data);
                this.template.convertAndSend("/topic/response", new MessageForClient(HtmlUtils.htmlEscape(data)));
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
        } catch (IOException e) {
            logger.error("error", e);
        }
    }

}
