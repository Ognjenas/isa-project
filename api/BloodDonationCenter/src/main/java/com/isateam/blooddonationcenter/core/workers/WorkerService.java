package com.isateam.blooddonationcenter.core.workers;

import com.isateam.blooddonationcenter.core.errorhandling.BadRequestException;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserEntityDao;
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
}
