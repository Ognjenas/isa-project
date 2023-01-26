package com.isateam.blooddonationcenter.core.websockets;

import com.isateam.blooddonationcenter.core.blooddonations.BloodDonation;
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



    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public BloodDonation broadcastNotification() {
//        Map<String, String> messageConverted = new HashMap<String, String>();
//        messageConverted.put("hey", "nigger");
//
//        if (messageConverted != null) {
//            if (messageConverted.containsKey("toId") && messageConverted.get("toId") != null
//                    && !messageConverted.get("toId").equals("")) {
//                this.simpMessagingTemplate.convertAndSend("/socket-publisher/" + messageConverted.get("toId"),
//                        messageConverted);
//                this.simpMessagingTemplate.convertAndSend("/socket-publisher/" + messageConverted.get("fromId"),
//                        messageConverted);
//            } else {
//                this.simpMessagingTemplate.convertAndSend("/socket-publisher", messageConverted);
//            }
//        }
        return BloodDonation.builder().id(1L).build();
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
