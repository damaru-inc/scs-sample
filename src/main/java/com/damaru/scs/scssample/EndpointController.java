package com.damaru.scs.scssample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EndpointController {

    private Logger log = LoggerFactory.getLogger(EndpointController.class);
    private int generatedOrderId;

    @Autowired
    MessageService messageService;

    @PostMapping("/")
    public void post(@RequestBody String body) {
        messageService.send(OrderAction.create, ++generatedOrderId, body);
    }

    @PostMapping("/{orderId}")
    public void post(@PathVariable("orderId") Integer orderId, @RequestBody String body) {
        messageService.send(OrderAction.update, orderId, body);
    }

    @DeleteMapping("/{orderId}")
    public void cancel(@PathVariable("orderId") Integer orderId, @RequestBody String body) {
        messageService.send(OrderAction.cancel, orderId, body);
    }

}
