package com.isateam.blooddonationcenter.core.workers.interfaces;
import com.isateam.blooddonationcenter.core.bloodstorage.dto.BloodStorageDto;
import com.isateam.blooddonationcenter.core.workers.Worker;
import com.isateam.blooddonationcenter.core.workers.dtos.UpdateWorkerDto;
import com.isateam.blooddonationcenter.core.workers.dtos.Worker2UpdateDto;

public interface IWorkerService {
    Worker create(Worker worker);

    Worker getById(long id);

    Worker2UpdateDto update(UpdateWorkerDto updateWorkerDto);

    long getCenterIdByWorkerId(long id);

    BloodStorageDto getBloodStorageForWorker(long loggedId);
}
