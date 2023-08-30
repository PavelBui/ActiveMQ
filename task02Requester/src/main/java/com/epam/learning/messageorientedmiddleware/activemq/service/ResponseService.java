package com.epam.learning.messageorientedmiddleware.activemq.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    @JmsListener(destination = "replyChanel")
    public void receiveMessage(String message) {
        System.out.println("Requester received next message: " + message);
    }
}
