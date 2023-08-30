package com.epam.learning.messageorientedmiddleware.activemq;

import com.epam.learning.messageorientedmiddleware.activemq.service.RequestService;
import com.epam.learning.messageorientedmiddleware.activemq.service.ResponseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ActivemqTask02RequesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqTask02RequesterApplication.class, args);
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ActivemqTask02RequesterApplication.class, args);

		RequestService requestService = configurableApplicationContext.getBean(RequestService.class);
		ResponseService responseService = configurableApplicationContext.getBean(ResponseService.class);

		requestService.sendMessage("requestChanel", "Initial request");
		boolean readyForRequest = false;

		for (int i = 1; i < 11; i++) {
			requestService.sendMessage("requestChanel", "Request " + i);
		}

	}

}
