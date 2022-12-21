package com.isateam.blooddonationcenter.core.appointments;

import com.isateam.blooddonationcenter.core.appointments.dtos.CreateAppointmentDTO;
import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final IAppointmentService appointmentService;


    @GetMapping("{id}")
    public Appointment findById(@PathVariable long id) {
        return appointmentService.findById(id);
    }

    @GetMapping("/centers/{id}/date/{date}/free")
    public List<Appointment> getAllFreeByDateAndCenter(@PathVariable long id, @PathVariable LocalDate date)  {
        return appointmentService.getAllFreeByDateAndCenter(date, id);
    }

    @PatchMapping("{id}")
    public void reserveAppointment(@PathVariable long id) {
        appointmentService.reserveAppointment(id);
    }

    @PostMapping("")
    public Appointment create(@Valid @RequestBody CreateAppointmentDTO dto) {
        return appointmentService.create(dto.mapToModel());
    }
}
