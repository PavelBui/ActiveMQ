package com.epam.learning.messageorientedmiddleware.activemq.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    public static final String REQUEST_CHANEL = "requestChanel";
    private static final String REPLY_CHANEL = "replyChanel";
    private static final int QUEUE_ASKING_TIMEOUT_MS = 5000;
    private static final int QUEUE_ASKING_LIMIT = 10;
    private static Queue<String> messageQueue = new LinkedList<>();
    private static int queueAskingIterator = 1;

    @Transactional
    public void sendNextMessage(String destination) throws InterruptedException {
        if (!messageQueue.isEmpty()) {
            jmsTemplate.convertAndSend(destination, messageQueue.poll());
            queueAskingIterator = 1;
        } else {
            if (queueAskingIterator > QUEUE_ASKING_LIMIT) {
                System.out.println("Queue of messages is empty. I'm have asked a lot of the times. Needs manual event");
            } else {
                System.out.println("Queue of messages is empty. This is my " + queueAskingIterator + "'th ask. I'm going to sleep " + QUEUE_ASKING_TIMEOUT_MS + "ms");
                sleep(QUEUE_ASKING_TIMEOUT_MS);
                queueAskingIterator++;
                sendNextMessage(REQUEST_CHANEL);
            }
        }
    }

    @JmsListener(destination = REPLY_CHANEL)
    public void receiveMessage(String message) throws InterruptedException {
        System.out.println("Requester received next message: " + message);
        sendNextMessage(REQUEST_CHANEL);
    }

    public void addMessage(String message) {
        messageQueue.add(message);
    }

    public void addMessages(List<String> messages) {
        messageQueue.addAll(messages);
    }
}
