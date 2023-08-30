package com.epam.learning.messageorientedmiddleware.activemq.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    @JmsListener(destination = "requestChanel")
    @SendTo("replyChanel")
    public String receiveMessage(String message) {
        System.out.println("Replier received next message: " + message);
        return message + " answer";
    }
}