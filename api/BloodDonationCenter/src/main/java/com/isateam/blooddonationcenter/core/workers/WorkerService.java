package com.isateam.blooddonationcenter.core.workers;

import com.isateam.blooddonationcenter.core.bloodstorage.BloodStorage;
import com.isateam.blooddonationcenter.core.bloodstorage.dto.BloodStorageDto;
import com.isateam.blooddonationcenter.core.bloodstorage.dto.BloodStorageInstanceDto;
import com.isateam.blooddonationcenter.core.bloodstorage.interfaces.IBloodStorageDao;
import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.centers.interfaces.CenterDao;
import com.isateam.blooddonationcenter.core.errorhandling.BadRequestException;
import com.isateam.blooddonationcenter.core.errorhandling.NotFoundException;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserEntityDao;
import com.isateam.blooddonationcenter.core.workers.dtos.UpdateWorkerDto;
import com.isateam.blooddonationcenter.core.workers.dtos.Worker2UpdateDto;
import com.isateam.blooddonationcenter.core.workers.interfaces.IWorkerDao;
import com.isateam.blooddonationcenter.core.workers.interfaces.IWorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService implements IWorkerService {

    private final IWorkerDao workerDao;
    private final IUserEntityDao userEntityDao;


    private final CenterDao centerDao;

    @Override
    public Worker create(Worker worker) {
        if (userEntityDao.findByEmail(worker.getUser().getEmail()).isPresent()) {
            throw new BadRequestException("Given email is already in use");
        }
        workerDao.save(worker);
        return worker;
    }

    @Override
    public Worker getById(long id) {
        Worker worker=workerDao.findByUser_Id(id);
        if(worker==null) new NotFoundException("Worker does not exist");
        return worker;
    }

    @Override
    public Worker2UpdateDto update(UpdateWorkerDto updateWorkerDto) {
        Worker worker=workerDao.findByUser_Id(updateWorkerDto.getId());
        if(worker==null) new NotFoundException("Worker does not exist");
        Worker newWorker=updateWorkerDto.mapToModel(worker.getUser());
        newWorker.setCenter(worker.getCenter());
        workerDao.save(newWorker);
        return new Worker2UpdateDto(newWorker);
    }

    @Override
    public long getCenterIdByWorkerId(long id) {
        Worker worker = getById(id);
        return worker.getCenter().getId();
    }

    @Override
    public BloodStorageDto getBloodStorageForWorker(long loggedId) {
        long centerId = getCenterIdByWorkerId(loggedId);
        Center center =centerDao.findById(centerId).orElseThrow( () -> new NotFoundException("Center does not exist!"));
        List<BloodStorageInstanceDto> centerBloodStorage = new ArrayList<>();
        center.getBloodStorage().stream().forEach(bs ->  centerBloodStorage.add(new BloodStorageInstanceDto(bs.getBloodType(),bs.getQuantity())));
        return new BloodStorageDto(centerBloodStorage);
    }


}
