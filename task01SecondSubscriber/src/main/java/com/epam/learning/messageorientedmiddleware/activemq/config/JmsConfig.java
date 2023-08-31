package com.epam.learning.messageorientedmiddleware.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJms
@Configuration
public class JmsConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Value("${spring.activemq.user}")
    private String user;

    @Value("${spring.activemq.password}")
    private String password;

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(new ActiveMQConnectionFactory(user, password, brokerUrl));
        cachingConnectionFactory.setReconnectOnException(true);
        return cachingConnectionFactory;
    }

    public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory defaultContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultContainerFactory.setConnectionFactory(connectionFactory());
        defaultContainerFactory.setConcurrency("1-1");
        return defaultContainerFactory;
    }

}
