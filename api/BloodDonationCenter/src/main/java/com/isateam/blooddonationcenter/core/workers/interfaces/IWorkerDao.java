package com.isateam.blooddonationcenter.core.workers.interfaces;
import com.isateam.blooddonationcenter.core.workers.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWorkerDao extends JpaRepository<Worker, Long> {

}

