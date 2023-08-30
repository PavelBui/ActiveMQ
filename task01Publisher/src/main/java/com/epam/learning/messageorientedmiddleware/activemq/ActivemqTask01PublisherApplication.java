package com.epam.learning.messageorientedmiddleware.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ActivemqTask01PublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqTask01PublisherApplication.class, args);
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ActivemqTask01PublisherApplication.class, args);

//		JmsTemplate jmsTemplate = configurableApplicationContext.getBean(JmsTemplate.class);
//		jmsTemplate.convertAndSend("test123", "test Hello!");

		Sender sender = configurableApplicationContext.getBean(Sender.class);
		for (int i = 0; i < 10; i++) {
			sender.sendMessage("test123", "test Hello " + i + "!");
		}

	}

}
