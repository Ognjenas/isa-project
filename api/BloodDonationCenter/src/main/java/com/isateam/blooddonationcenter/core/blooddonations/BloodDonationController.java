package com.isateam.blooddonationcenter.core.blooddonations;

import com.isateam.blooddonationcenter.core.appointments.Appointment;
import com.isateam.blooddonationcenter.core.appointments.dtos.CreateAppointmentDTO;
import com.isateam.blooddonationcenter.core.blooddonations.BloodDonation;
import com.isateam.blooddonationcenter.core.blooddonations.dto.CreateBloodDonationDTO;
import com.isateam.blooddonationcenter.core.blooddonations.interfaces.IBloodDonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/bloodDonation")
@RequiredArgsConstructor
public class BloodDonationController {
    private final IBloodDonationService bloodDonationService;

    @PreAuthorize("hasRole('WORKER')")
    @PostMapping
    public void create(@RequestBody CreateBloodDonationDTO dto) {
        bloodDonationService.create(dto.mapToModel());
    }

}
