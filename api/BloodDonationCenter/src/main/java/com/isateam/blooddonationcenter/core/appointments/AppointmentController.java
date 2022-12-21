package com.isateam.blooddonationcenter.core.appointments;

import com.isateam.blooddonationcenter.core.appointments.dtos.CreateAppointmentDTO;
import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
    public List<Appointment> getAllFreeByDateAndCenter(@PathVariable long id,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date)  {
        List<Appointment> appointments = appointmentService.getAllFreeByDateAndCenter(date, id);
        return appointments;
    }

    @PatchMapping("{id}/user/{userId}")
    public void reserveAppointment(@PathVariable long id, @PathVariable long userId) {
        appointmentService.reserve(id, userId);
    }

    @PostMapping("")
    public Appointment create(@Valid @RequestBody CreateAppointmentDTO dto) {
        return appointmentService.create(dto.mapToModel());
    }
}
