package com.sdge.contractjms.config;

import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;

import javax.jms.ConnectionFactory;

/**
 * Created by ddmungui on 3/30/2017.
 */
@Configuration
public class JmsConfig {


    @Bean
    public JmsTransactionManager createJmsTrnsactionManager(final ConnectionFactory connectionFactory){

        JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
        jmsTransactionManager.setConnectionFactory(connectionFactory);

        return jmsTransactionManager;
    }


    @Bean
    public JmsComponent createJmsCompponent(final ConnectionFactory connectionFactory, final JmsTransactionManager jmsTransactionManager, @Value("${max.concurrent.consumers}") final int maxConcurrentConsumer)
    {
        JmsComponent jmsComponent = JmsComponent.jmsComponentTransacted(connectionFactory,jmsTransactionManager);
        jmsComponent.setMaxConcurrentConsumers(maxConcurrentConsumer);
        return jmsComponent;
    }
}
