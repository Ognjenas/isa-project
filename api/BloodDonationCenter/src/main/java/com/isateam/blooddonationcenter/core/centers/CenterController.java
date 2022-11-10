package com.isateam.blooddonationcenter.core.centers;

import com.isateam.blooddonationcenter.core.centers.dtos.AllCentersDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CreateCenterDTO;
import com.isateam.blooddonationcenter.core.centers.interfaces.ICenterService;
import com.isateam.blooddonationcenter.core.users.dtos.CreateUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/centers")
@RequiredArgsConstructor
public class CenterController {

    private final ICenterService centerService;

    @PostMapping
    public void register(@Valid @RequestBody CreateCenterDTO createCenterDTO) {centerService.create(createCenterDTO.map());
    }

    @GetMapping
    public AllCentersDto getAll() {
        return centerService.getAll();
    }
}
