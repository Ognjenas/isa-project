package com.isateam.blooddonationcenter.core.rabbit;

import com.google.gson.Gson;
import com.isateam.blooddonationcenter.core.bloodcontracts.BloodContract;
import com.isateam.blooddonationcenter.core.websockets.WebSocketController;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationConsumer {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @RabbitListener(queues = "geolocation.queue")
    public void locationHandler(String locationString) {
        if(locationString.equals("null")) return;
        System.out.println(locationString);
        Location location = convert(locationString);
        simpMessagingTemplate.convertAndSend("/topic/greetings", location);
    }

    public Location convert(String location) {
        Gson gson = new Gson();
        return gson.fromJson(location,Location.class);
    }

}
