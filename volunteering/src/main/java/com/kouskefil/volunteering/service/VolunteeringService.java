package com.kouskefil.volunteering.service;

import com.kouskefil.volunteering.DTOs.VolunteeringDTO;
import com.kouskefil.volunteering.Model.Volunteering;
import com.kouskefil.volunteering.repository.VolunteerRepository;
import com.kouskefil.volunteering.utls.ShiftType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteeringService {
    private final VolunteerRepository volunteerRepository;

    public Volunteering save(VolunteeringDTO dto) {
        boolean alreadySubmitted = volunteerRepository.existsByEmployeeIdAndSpecialDayId(
                dto.employeeId(), dto.specialDayId()
        );
        if (alreadySubmitted) {
            throw new IllegalStateException("Your volunteering already exists");
        }
        Volunteering volunteering = Volunteering.builder()
                .employeeId(dto.employeeId())
                .specialDayId(dto.specialDayId())
                .selectedShifts(dto.selectedShifts())
                .submittedAt(LocalDateTime.now())
                .build();

        return volunteerRepository.save(volunteering);
    }

    public List<Volunteering> getByEmployeeId(String employeeId) {
        return volunteerRepository.findByEmployeeId(employeeId);
    }
    public List<Volunteering> getByDayAndShift(String specialDayId, ShiftType shiftType) {
        return volunteerRepository.findBySpecialDayIdAndSelectedShiftsContaining(specialDayId, shiftType);
    }

    public List<Volunteering> getAllForDay(String specialDayId) {
        return volunteerRepository.findBySpecialDayId(specialDayId);
    }
}
