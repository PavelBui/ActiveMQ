package com.epam.learning.messageorientedmiddleware.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJms
@Configuration
public class JmsConfig {//implements JmsListenerConfigurer {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Value("${spring.activemq.user}")
    private String user;

    @Value("${spring.activemq.password}")
    private String password;

//	@Bean
//	public JmsListenerContainerFactory myFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
//		DefaultJmsListenerContainerFactory defaultContainerFactory = new DefaultJmsListenerContainerFactory();
//		configurer.configure(defaultContainerFactory, connectionFactory);
//		return defaultContainerFactory;
//	}

//    @Bean
//    public MessageConverter jacksonJmsMessageConvertor() {
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setTargetType(MessageType.TEXT);
//        converter.setEncodingPropertyName("_type");
//        return converter;
//    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
//        SingleConnectionFactory singleConnectionFactory = new SingleConnectionFactory(new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616"));
//        singleConnectionFactory.setReconnectOnException(true);
//        singleConnectionFactory.setClientId("clientId");
//        return singleConnectionFactory;
        return new ActiveMQConnectionFactory(user, password, brokerUrl);
    }

    @Bean
    public JmsTemplate jmsTemplate()
    {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
//        jmsTemplate.setMessageConverter(jacksonJmsMessageConvertor());
        jmsTemplate.setDeliveryPersistent(true);
        jmsTemplate.setSessionTransacted(true);
        return jmsTemplate;
    }

    public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory defaultContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultContainerFactory.setConnectionFactory(connectionFactory());
//        defaultContainerFactory.setMessageConverter(jacksonJmsMessageConvertor());
        defaultContainerFactory.setConcurrency("1-1");
        defaultContainerFactory.setTransactionManager(jmsTransactionManager());
        return defaultContainerFactory;
    }

//    @Bean
//    public MyMessageListener jmsMessageListener() {
//        return new MyMessageListener();
//    }
//
//    @Override
//    public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
//        SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
//        endpoint.setMessageListener(jmsMessageListener());
//        endpoint.setDestination("test12345");
//        endpoint.setId("1-2-3-4-5");
//        endpoint.setSubscription("Subscription");
//        endpoint.setConcurrency("1");
//        registrar.setContainerFactory(defaultJmsListenerContainerFactory());
//        registrar.registerEndpoint(endpoint, defaultJmsListenerContainerFactory());
//    }

    @Bean
    public PlatformTransactionManager jmsTransactionManager() {
        return new JmsTransactionManager(connectionFactory());
    }


}
