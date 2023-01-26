package com.isateam.blooddonationcenter.core.websockets;

import com.isateam.blooddonationcenter.core.blooddonations.BloodDonation;
import com.isateam.blooddonationcenter.core.rabbit.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RequiredArgsConstructor
@Controller
public class WebSocketController {


    private final SimpMessagingTemplate simpMessagingTemplate;

    @SendTo("/topic/greetings")
    public Location broadcastNotification(Location location) {
        return location;
    }

//    @SuppressWarnings("unchecked")
//    private Map<String, String> parseMessage(String message) {
//        ObjectMapper mapper = new ObjectMapper();
//        Map<String, String> retVal;
//
//        try {
//            retVal = mapper.readValue(message, Map.class); // parsiranje JSON stringa
//        } catch (IOException e) {
//            retVal = null;
//        }
//
//        return retVal;
//    }
}
