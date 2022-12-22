package com.isateam.blooddonationcenter.core.appointments;

import com.isateam.blooddonationcenter.core.appointments.dtos.AppointmentsForShowDto;
import com.isateam.blooddonationcenter.core.appointments.dtos.CreateAppointmentDTO;
import com.isateam.blooddonationcenter.core.appointments.dtos.ShowAppointmentDTO;
import com.isateam.blooddonationcenter.core.appointments.dtos.FreeAppointmentsDTO;
import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentService;
import com.isateam.blooddonationcenter.core.utils.session.UserUtils;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final UserUtils userUtils;

    private final IUserService userService;


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
        appointmentService.reserve(id, userUtils.getLoggedId());
    }

    @PostMapping
    public Appointment create(@Valid @RequestBody CreateAppointmentDTO dto) {
        return appointmentService.create(dto.mapToModel());
    }

    @GetMapping("/{centerId}/free")
    public FreeAppointmentsDTO getFreeAppointmentsFromCenter(@PathVariable("centerId") long centerId, @RequestParam(name="sortby") String orderBy) {
        return new FreeAppointmentsDTO(appointmentService.getAllFutureAppointments(centerId, orderBy));
    }

    @GetMapping("/by-user")
    public FreeAppointmentsDTO getAppointmentsByUser() {
        return new FreeAppointmentsDTO(appointmentService.getAllFutureAppointmentsByUser(userUtils.getLoggedId()));
    }

    @PatchMapping("/cancel/{appointmentId}")
    public void cancelAppointment(@PathVariable long appointmentId) {
        appointmentService.cancel(appointmentId, userUtils.getLoggedId());
    }

    @PreAuthorize("hasRole('WORKER')")
    @GetMapping("/for-worker")
    public List<AppointmentsForShowDto> getShowAppointments(){
        return appointmentService.getAllAppointmentsForCenter(userUtils.getLoggedId());
    }

}
