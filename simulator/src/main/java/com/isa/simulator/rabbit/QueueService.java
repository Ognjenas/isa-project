package com.isa.simulator.rabbit;

import com.google.gson.Gson;
import com.isa.simulator.directions.Direction;
import com.isa.simulator.directions.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueueService {
    private final RabbitTemplate template;

    private final String contractQueue="geolocation.queue";
    private final String exchange = "geo.exchange";
    private final String routingKey = "geo.geolocation.routing-key";

    public void sendLocation(Location location) {
        String message = convertToString(location);
        this.template.convertAndSend(exchange, routingKey, message);
    }


    private String convertToString(Location location) {
        Gson gson = new Gson();
        return gson.toJson(location);
    }
}
