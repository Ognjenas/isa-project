package com.isateam.blooddonationcenter.core.rabbit;

import com.google.gson.Gson;
import com.isateam.blooddonationcenter.core.bloodcontracts.BloodContract;
import com.isateam.blooddonationcenter.core.bloodcontracts.BloodContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BloodContractConsumer {
    private final BloodContractService contractService;

    @RabbitListener(queues = "contract.queue")
    public void contractHandler(String contractStr) {
        if(contractStr.equals("null")) return;
        System.out.println(contractStr);
        BloodContract contract = convertStringToBloodContract(contractStr);
        contractService.createContract(contract);
    }
    public BloodContract convertStringToBloodContract(String contract) {
        Gson gson = new Gson();
        return gson.fromJson(contract,BloodContract.class);
    }
}




