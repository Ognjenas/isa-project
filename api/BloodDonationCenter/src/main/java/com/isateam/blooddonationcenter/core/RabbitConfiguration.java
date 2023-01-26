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

    String locationQueue="geolocation.queue";
    String locationExchange = "geo.exchange";
    String locationRoutingKey = "geo.geolocation.routing-key";

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
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUri(connection);
        return connectionFactory;
    }

    @Bean
    public Queue queue2() {
        return new Queue(locationQueue, true);
    }

    @Bean
    public DirectExchange exchange2() {
        return new DirectExchange(locationExchange);
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(exchange2()).with(locationRoutingKey);
    }
}
