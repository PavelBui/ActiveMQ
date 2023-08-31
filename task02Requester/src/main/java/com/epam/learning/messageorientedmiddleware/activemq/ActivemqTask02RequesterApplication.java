package com.epam.learning.messageorientedmiddleware.activemq;

import com.epam.learning.messageorientedmiddleware.activemq.service.RequestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

import static java.lang.Thread.sleep;

@SpringBootApplication
public class ActivemqTask02RequesterApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(ActivemqTask02RequesterApplication.class, args);
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ActivemqTask02RequesterApplication.class, args);

		RequestService requestService = configurableApplicationContext.getBean(RequestService.class);

		requestService.addMessage("Initial request");
		requestService.addMessages(
				List.of("Request 1",
						"Request 2",
						"Request 3",
						"Request 4",
						"Request 5",
						"Request 6",
						"Request 7",
						"Request 8",
						"Request 9",
						"Request 10"));
		requestService.sendNextMessage(RequestService.REQUEST_CHANEL);
		requestService.addMessages(
				List.of("Request 11",
						"Request 12",
						"Request 13",
						"Request 14",
						"Request 15",
						"Request 16",
						"Request 17",
						"Request 18",
						"Request 19",
						"Request 20"));
		sleep(2000);
		requestService.addMessages(
				List.of("Request 21",
						"Request 22",
						"Request 23",
						"Request 24",
						"Request 25",
						"Request 26",
						"Request 27",
						"Request 28",
						"Request 29",
						"Request 30"));
	}
}
