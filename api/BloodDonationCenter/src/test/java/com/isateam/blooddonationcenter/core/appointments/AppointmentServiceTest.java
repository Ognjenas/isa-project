package com.isateam.blooddonationcenter.core.appointments;

import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentDao;
import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentService;
import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.users.User;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class AppointmentServiceTest {

    @Autowired
    private IAppointmentDao appointmentDao;

    @Autowired
    private IAppointmentService appointmentService;

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testOptimisticLockingScenario() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                var appointment = appointmentDao.findById(600L);
                appointment.orElseThrow().setState(AppointmentState.TAKEN);
                try { Thread.sleep(3000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
                appointmentDao.save(appointment.orElseThrow());

            }
        });
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                var appointment = appointmentDao.findById(600L);
                appointment.orElseThrow().setState(AppointmentState.TAKEN);
                appointmentDao.save(appointment.orElseThrow());
            }
        });
        try {
            future1.get();
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass());
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

    }

    @Test
    public void testConcurrentAccess() throws InterruptedException {
        List<Appointment> appointmentListBefore = appointmentDao.findAllByCenter_Id(1L);
        LocalDateTime nowTime = LocalDateTime.now();
        Appointment appointment1 = Appointment.builder()
                .id(5000l)
                .center(Center.builder()
                        .id(1L)
                        .build())
                .startTime(nowTime.plusDays(4))
                .endTime(nowTime.plusDays(4).plusMinutes(30))
                .duration(30)
                .state(AppointmentState.FREE)
                .build();
        Appointment appointment2 = Appointment.builder()
                .id(5001l)
                .center(Center.builder()
                        .id(1L)
                        .build())
                .startTime(nowTime.plusDays(4))
                .endTime(nowTime.plusDays(4).plusMinutes(30))
                .duration(30)
                .state(AppointmentState.FREE)
                .build();


        // Start two threads to schedule the appointments concurrently
        Thread thread1 = new Thread(() -> appointmentService.create(appointment1));
        Thread thread2 = new Thread(() -> appointmentService.create(appointment2));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        // Check if only one appointment was scheduled
        List<Appointment> appointmentListAfter = appointmentDao.findAllByCenter_Id(1L);
        assertEquals(appointmentListBefore.size() + 1, appointmentListAfter.size());
    }
}