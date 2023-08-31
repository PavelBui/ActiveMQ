package com.epam.learning.messageorientedmiddleware.activemq.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService {

    @JmsListener(destination = "pub-sub-topic", containerFactory = "jmsListenerContainerFactory", subscription = "durable")
    public void receiveMessage(String message) {
        System.out.println("Order received is: " + message);
    }

}
