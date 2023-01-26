package com.isateam.blooddonationcenter.core.appointments;

import com.isateam.blooddonationcenter.core.appointments.dtos.*;
import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentService;
import com.isateam.blooddonationcenter.core.utils.session.UserUtils;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
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
    public List<ShowAppointmentDTO> getAllFreeByDateAndCenter(@PathVariable long id, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date)  {
        return appointmentService.getAllFreeByDateAndCenter(date, id)
                .stream()
                .map(a -> new ShowAppointmentDTO(a)).toList();
    }

    @GetMapping("/date/{date}/free")
    public List<ShowAppointmentDTO> getAllFreeAppointmentsWithCenters(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime date, @PathParam("direction") String direction) {
        String orderDirection = direction == null? "desc": direction;
        return appointmentService.getAllFreeByDateTime(date, orderDirection)
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

    @PostMapping("/create-for-self")
    public Appointment createForSelf(@Valid @RequestBody CreateAppointmentDTO dto) {
        Appointment appointment = dto.mapToModel();
        appointment.setUser(userService.getOne(userUtils.getLoggedId()));
        appointment.setState(AppointmentState.TAKEN);
        return appointmentService.createForSelf(appointment);
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


    @GetMapping("/for-worker")
    public List<AppointmentsForShowDto> getShowAppointments(){
        System.out.println(userUtils.getLoggedId());
        return appointmentService.getAllAppointmentsForCenter(userUtils.getLoggedId());
    }

    @GetMapping("donation-history/{userId}")
    public List<DonationHistoryDTO> getDonationHistory(@PathVariable long userId) {
        List<Appointment> appointments =  appointmentService.getUserDonationHistory(userId, userUtils.getLoggedId());
        return appointments.stream().map(a -> new DonationHistoryDTO(a)).toList();
    }

    @GetMapping("/user/past")
    public List<ShowAppointmentDTO> getAllPastAppointments(@RequestParam(name="sortby") String orderBy) {
        return appointmentService.getAllUsersPastAppointments(userUtils.getLoggedId(), orderBy)
                .stream()
                .map(ShowAppointmentDTO::new)
                .toList();
    }

    @GetMapping("check-appointment/{id}")
    public boolean checkAppointment(@PathVariable long id){
         return appointmentService.checkAppointment(id);
    }
}
