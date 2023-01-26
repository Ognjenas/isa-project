package com.isateam.blooddonationcenter.core.rabbit;

import com.google.gson.Gson;
import com.isateam.blooddonationcenter.core.bloodcontracts.BloodContract;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueueService {
    private final RabbitTemplate template;

    private final String contractQueue="contract.queue";
    private final String exchange = "dir.exchange";
    private final String routingKey = "dir.contract.routing-key";

    public void sendContract(BloodContract contract) {
        String message = convertContractToString(contract);
        this.template.convertAndSend(exchange, routingKey, message);
    }


    private String convertContractToString(BloodContract contract) {
        Gson gson = new Gson();
        return gson.toJson(contract);
    }
}
