package com.epam.learning.messageorientedmiddleware.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    /*
    @JmsListener(destination = "test123", containerFactory = "myFactory")
    public void receiveMessage(String message) {
        System.out.println("Order received is: " + message);
    }
    */

    @JmsListener(destination = "test123")
    public void receiveMessage(String message) {
        System.out.println("Order received is: " + message);
    }

}
