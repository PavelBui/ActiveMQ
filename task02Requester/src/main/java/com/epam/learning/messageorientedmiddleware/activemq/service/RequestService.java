package com.epam.learning.messageorientedmiddleware.activemq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.lang.Thread.sleep;

@Service
public class RequestService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${activemq.task01.request-channel}")
    private String requestChannel;
    @Value("${activemq.task01.queue-asking-timeout-ms}")
    private int queueAskingTimeoutMs;
    @Value("${activemq.task01.queue-asking-limit}")
    private int queueAskingLimit;
    private static Queue<String> messageQueue = new LinkedList<>();
    private static int queueAskingIterator = 1;

    @Transactional
    public void sendNextMessage() throws InterruptedException {
        if (!messageQueue.isEmpty()) {
            jmsTemplate.convertAndSend(requestChannel, messageQueue.poll());
            queueAskingIterator = 1;
        } else {
            if (queueAskingIterator > queueAskingLimit) {
                System.out.println("Queue of messages is empty. I have already asked many times. Manual event required");
            } else {
                System.out.println("Queue of messages is empty. This is my " + queueAskingIterator + "'th ask. I'm going to sleep " + queueAskingTimeoutMs + "ms");
                sleep(queueAskingTimeoutMs);
                queueAskingIterator++;
                sendNextMessage();
            }
        }
    }

    @JmsListener(destination = "${activemq.task01.reply-channel}")
    public void receiveMessage(String message) throws InterruptedException {
        System.out.println("Requester received next message: " + message);
        sendNextMessage();
    }

    public void addMessage(String message) {
        messageQueue.add(message);
    }

    public void addMessages(List<String> messages) {
        messageQueue.addAll(messages);
    }
}
