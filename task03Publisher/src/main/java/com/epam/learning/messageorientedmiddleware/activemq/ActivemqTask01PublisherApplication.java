package com.epam.learning.messageorientedmiddleware.activemq;

import com.epam.learning.messageorientedmiddleware.activemq.service.PublishService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ActivemqTask01PublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqTask01PublisherApplication.class, args);
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ActivemqTask01PublisherApplication.class, args);

		PublishService publishService = configurableApplicationContext.getBean(PublishService.class);
		for (int i = 1; i < 11; i++) {
			publishService.sendMessage("VirtualTopic.my-virtual-topic", "test Hello " + i + "!");
		}

	}

}
