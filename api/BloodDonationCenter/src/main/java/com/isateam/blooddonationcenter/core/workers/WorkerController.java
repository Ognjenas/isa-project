package com.isateam.blooddonationcenter.core.workers;

import com.isateam.blooddonationcenter.core.bloodstorage.dto.BloodStorageDto;
import com.isateam.blooddonationcenter.core.utils.session.UserUtils;
import com.isateam.blooddonationcenter.core.workers.dtos.CreateWorkerDto;
import com.isateam.blooddonationcenter.core.workers.dtos.UpdateWorkerDto;
import com.isateam.blooddonationcenter.core.workers.dtos.Worker2UpdateDto;
import com.isateam.blooddonationcenter.core.workers.interfaces.IWorkerService;
import lombok.RequiredArgsConstructor;
import org.hibernate.jdbc.Work;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/workers")
public class WorkerController {
    private final IWorkerService workerService;

    private final UserUtils userUtils;

    @PostMapping
    public void register(@Valid @RequestBody CreateWorkerDto createWorkerDto) {workerService.create(createWorkerDto.map());}

    @GetMapping("/{id}")
    public Worker2UpdateDto getById(@PathVariable("id") long id){
        Worker worker=workerService.getById(id);
        return new Worker2UpdateDto(worker);
    }

    @GetMapping("/center")
    public long getCenterId(){
        return workerService.getCenterIdByWorkerId(userUtils.getLoggedId());
    }

    @GetMapping("/blood-storage")
    public BloodStorageDto getBloodStorage(){
        return workerService.getBloodStorageForWorker(userUtils.getLoggedId());
    }

    @PutMapping
    public Worker2UpdateDto updateWorker(@RequestBody UpdateWorkerDto updateWorkerDto){
        return workerService.update(updateWorkerDto);
    }

}
