package com.isateam.blooddonationcenter.core.mock;


import com.isateam.blooddonationcenter.core.bloodcontracts.BloodContract;
import com.isateam.blooddonationcenter.core.blooddonations.BloodType;
import com.isateam.blooddonationcenter.core.rabbit.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/mock")
public class MockController {
    private final QueueService queueService;

    @GetMapping
    public String testContract() {
        BloodContract contract = BloodContract.builder()
                .email("stjepanovicsrdjan2000@gmail.com")
                .unit("ml")
                .type(BloodType.A_POSITIVE)
                .quantity(100)
                .centerId(1)
                .build();
        queueService.sendContract(contract);
        return "OK";
    }
}
