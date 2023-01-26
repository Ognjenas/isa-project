package com.isateam.blooddonationcenter.core.centers;

import com.isateam.blooddonationcenter.core.appointments.Appointment;
import com.isateam.blooddonationcenter.core.appointments.AppointmentState;
import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentDao;
import com.isateam.blooddonationcenter.core.centers.dtos.AllCentersDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CenterDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CreateCenterDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CriteriaDto;
import com.isateam.blooddonationcenter.core.centers.interfaces.CenterDao;
import com.isateam.blooddonationcenter.core.centers.interfaces.ICenterCustomDao;
import com.isateam.blooddonationcenter.core.centers.interfaces.ICenterService;
import com.isateam.blooddonationcenter.core.errorhandling.BadRequestException;
import com.isateam.blooddonationcenter.core.errorhandling.NotFoundException;
import com.isateam.blooddonationcenter.core.users.Address;
import com.isateam.blooddonationcenter.core.users.User;
import com.isateam.blooddonationcenter.core.users.UserRole;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserEntityDao;
import com.isateam.blooddonationcenter.core.workers.Worker;
import com.isateam.blooddonationcenter.core.workers.interfaces.IWorkerDao;
import com.isateam.blooddonationcenter.core.worktime.WorkTime;
import com.isateam.blooddonationcenter.core.worktime.dtos.WorkTimeDto;
import com.isateam.blooddonationcenter.core.worktime.interfaces.IWorkTimeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CenterService implements ICenterService {

    private final CenterDao centerDao;
    private final IUserEntityDao userEntityDao;
    private final ICenterCustomDao centerCustomDao;
    private final IAppointmentDao appointmentDao;
    private final IWorkTimeDao workTimeDao;
    private final IWorkerDao workerDao;

    @Override
    public AllCentersDto getAll(Map<String, String> map) {
        return mapToAllCentersDto(centerCustomDao.getFiltered(map));
    }
    @Override
    public Center create(Center center) {
        return centerDao.save(center);
    }

    @Override
    public Center update(Center center) {
        Center centerOld = centerDao.findById(center.getId())
                .orElseThrow(() -> new NotFoundException("Center doesnt exist"));

        center.setAverageGrade(centerOld.getAverageGrade());
        return centerDao.save(center);
    }


    @Override
    public void addWorkTimeToCenter(Set<WorkTime> mapWorkTimeToModel) {
        workTimeDao.saveAll(mapWorkTimeToModel);
    }

    @Override
    public CenterDto getById(long id,long user_id) {
        User user = userEntityDao.findById(user_id).orElseThrow(() -> new NotFoundException("User doesn't exist"));
        checkIfWorkerCenter(id,user);
        Center center=centerDao.findById(id).orElseThrow(() -> new NotFoundException("Center doesn't exist"));
        List<WorkTimeDto> workTimes= new ArrayList<>();
        center.getWorkTime().stream().forEach(workTime -> workTimes.add(new WorkTimeDto(workTime)));
        Collections.sort(workTimes,(a,b)-> { return a.getDay().compareTo(b.getDay()); });
        return CenterDto.builder()
                .address(center.getAddress())
                .name(center.getName())
                .description(center.getDescription())
                .averageGrade(center.getAverageGrade())
                .workTime(workTimes)
                .build();
    }
    
    private void checkIfWorkerCenter(long center_id, User user) {
        if(user.getRole()== UserRole.WORKER){
            Worker worker = workerDao.findByUser_Id(user.getId());
            if(worker==null)
                throw new NotFoundException("Worker doesn't exist");

            if(center_id!=worker.getCenter().getId())
                throw new BadRequestException("This center isn't from this Worker!");
        }
    }

    @Override
    public AllCentersDto getAllWithoutAppointment(LocalDateTime startTime){
        List<Center> centers = new ArrayList<Center>();
        List<Appointment> appointments = appointmentDao.findAllByStartTime(startTime);
        if(appointments.size() == 0){
            centers = centerDao.findAll();
        }
        else {
            centers = centerDao.findDistinctByAppointmentsIsNotIn(appointments);
        }
        return mapToAllCentersDto(centers);
    }


    private AllCentersDto mapToAllCentersDto(List<Center> centers) {
        return new AllCentersDto(centers.stream().map(c -> CenterDto.builder()
                .id(c.getId())
                .address(c.getAddress())
                .averageGrade(c.getAverageGrade())
                .description(c.getDescription())
                .name(c.getName())
                .build()).collect(Collectors.toList()));
    }

    private List<CenterDto> mapToCenterDtoList(List<Center> centers){
        List<CenterDto> dtoList = new ArrayList<>();
        for (var center : centers) {
            CenterDto cen = new CenterDto();
                cen.setId(center.getId());
                cen.setAddress(center.getAddress());
                cen.setName(center.getName());
                cen.setDescription(center.getDescription());
                cen.setAverageGrade(center.getAverageGrade());
                dtoList.add(cen);
            }
        return  dtoList;
    }

}
