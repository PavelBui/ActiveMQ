package com.epam.learning.messageorientedmiddleware.activemq.service;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class PublishService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String destination, String message) {
        jmsTemplate.convertAndSend(new ActiveMQTopic(destination), message);
    }
}
