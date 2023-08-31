package com.epam.learning.messageorientedmiddleware.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
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
    public SingleConnectionFactory connectionFactory() {
        SingleConnectionFactory singleConnectionFactory = new CachingConnectionFactory(new ActiveMQConnectionFactory(user, password, brokerUrl));
        singleConnectionFactory.setReconnectOnException(true);
        singleConnectionFactory.setClientId("durable");
        return singleConnectionFactory;
    }

    @Bean
    public JmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory defaultContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultContainerFactory.setConnectionFactory(connectionFactory());
        defaultContainerFactory.setSubscriptionDurable(true);
        defaultContainerFactory.setConcurrency("1-1");
        return defaultContainerFactory;
    }

}
