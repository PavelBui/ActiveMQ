package com.epam.learning.messageorientedmiddleware.activemq.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @JmsListener(destination = "Consumer.my-second-consumer.VirtualTopic.my-virtual-topic")
    public void receiveMessage(String message) {
        System.out.println("Order received is: " + message);
    }

}
