package com.isateam.blooddonationcenter.core.workers;

import com.isateam.blooddonationcenter.core.workers.dtos.CreateWorkerDto;
import com.isateam.blooddonationcenter.core.workers.interfaces.IWorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workers")
public class WorkerController {
    private final IWorkerService workerService;

    @PostMapping
    public void register(@Valid @RequestBody CreateWorkerDto createWorkerDto) {workerService.create(createWorkerDto.map());}
}
