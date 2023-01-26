package com.isateam.blooddonationcenter.core.mock;


import com.isateam.blooddonationcenter.core.rabbit.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/mock")
public class MockController {

    private final QueueService queueService;


}
