package com.damaru.scs.scssample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.BinderHeaders;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class MessageService {
    private static final String DYNAMIC_BINDING = "dynamic";
    private static final String HEADER_DESTINATION = "solace_destination";

    private Logger log = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private StreamBridge streamBridge;

    @Bean
    public Consumer<Message<String>> orderConsumer(){
        return v -> {
            log.info(String.format("Received:    %-23s %s", v.getHeaders().get(HEADER_DESTINATION), v.getPayload()));
        };
    }

    public void send(OrderAction orderAction, int orderId, String order) {
        String topic = String.format("orders/%s/%03d", orderAction.name(), orderId);
        Message<String> message = MessageBuilder
                .withPayload(order)
                .setHeader(BinderHeaders.TARGET_DESTINATION, topic)
                .build();
        streamBridge.send(DYNAMIC_BINDING, message);
    }

}
