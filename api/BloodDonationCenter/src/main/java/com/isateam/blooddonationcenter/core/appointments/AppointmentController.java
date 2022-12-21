package com.isateam.blooddonationcenter.core.appointments;

import com.isateam.blooddonationcenter.core.appointments.dtos.CreateAppointmentDTO;
import com.isateam.blooddonationcenter.core.appointments.dtos.ShowAppointmentDTO;
import com.isateam.blooddonationcenter.core.appointments.dtos.FreeAppointmentsDTO;
import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentService;
import com.isateam.blooddonationcenter.core.centers.Center;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final IAppointmentService appointmentService;


    @GetMapping("/{id}")
    public Appointment findById(@PathVariable long id) {
        return appointmentService.findById(id);
    }

    @GetMapping("/centers/{id}/date/{date}/free")
    public List<ShowAppointmentDTO> getAllFreeByDateAndCenter(@PathVariable long id,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date)  {
        return appointmentService.getAllFreeByDateAndCenter(date, id)
                .stream()
                .map(a -> new ShowAppointmentDTO(a)).toList();
    }

    @GetMapping("/date/{date}/free")
    public List<ShowAppointmentDTO> getAllFreeAppointmentsWithCenters(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime date) {
        return appointmentService.getAllFreeByDateTime(date)
                .stream()
                .map(a -> new ShowAppointmentDTO(a)).toList();

    }

    @PatchMapping("/{id}/user/{userId}")
    public void reserveAppointment(@PathVariable long id, @PathVariable long userId) {
        appointmentService.reserve(id, userId);
    }

    @PostMapping
    public Appointment create(@Valid @RequestBody CreateAppointmentDTO dto) {
        return appointmentService.create(dto.mapToModel());
    }

    @GetMapping("/free")
    public FreeAppointmentsDTO getFreeAppointments() {
        return new FreeAppointmentsDTO(appointmentService.getAllFutureAppointments());
    }
}
