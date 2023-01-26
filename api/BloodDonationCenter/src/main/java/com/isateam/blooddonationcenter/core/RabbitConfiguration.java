package com.isateam.blooddonationcenter.core;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    String contractQueue="contract.queue";
    String exchange = "dir.exchange";
    String routingKey = "dir.contract.routing-key";
    String connection = "amqps://nfyetmku:LEFF5_Pbra4eSczMvpN1HIcPLPgU_nFr@stingray.rmq.cloudamqp.com/nfyetmku";

    @Bean
    public Queue queue() {
        return new Queue(contractQueue, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding binding(Queue q, DirectExchange e) {
        return BindingBuilder.bind(q).to(e).with(routingKey);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUri(connection);
        return connectionFactory;
    }
}
