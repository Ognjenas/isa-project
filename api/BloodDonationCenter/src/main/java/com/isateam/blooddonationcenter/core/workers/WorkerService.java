package com.isateam.blooddonationcenter.core.workers;

import com.isateam.blooddonationcenter.core.errorhandling.BadRequestException;
import com.isateam.blooddonationcenter.core.errorhandling.NotFoundException;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserEntityDao;
import com.isateam.blooddonationcenter.core.workers.dtos.UpdateWorkerDto;
import com.isateam.blooddonationcenter.core.workers.dtos.Worker2UpdateDto;
import com.isateam.blooddonationcenter.core.workers.interfaces.IWorkerDao;
import com.isateam.blooddonationcenter.core.workers.interfaces.IWorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkerService implements IWorkerService {

    private final IWorkerDao workerDao;
    private final IUserEntityDao userEntityDao;

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
        Worker worker=workerDao.findById(id).orElseThrow(()-> new NotFoundException("Worker does not exist"));
        return worker;
    }

    @Override
    public Worker2UpdateDto update(UpdateWorkerDto updateWorkerDto) {
        Worker worker=workerDao.findById(updateWorkerDto.getId()).orElseThrow(()-> new NotFoundException("Worker does not exist"));
        Worker newWorker=updateWorkerDto.mapToModel(worker.getUser());
        newWorker.setCenter(worker.getCenter());
        workerDao.save(newWorker);
        return new Worker2UpdateDto(newWorker);
    }
}
