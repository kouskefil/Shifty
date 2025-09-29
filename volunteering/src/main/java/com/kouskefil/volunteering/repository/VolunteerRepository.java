package com.kouskefil.volunteering.repository;

import com.kouskefil.volunteering.Model.Volunteering;
import com.kouskefil.volunteering.utls.ShiftType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VolunteerRepository extends MongoRepository<Volunteering, String> {
    List<Volunteering> findBySpecialDayId(String specialDayId);
    List<Volunteering> findBySpecialDayIdAndSelectedShiftsContaining(String specialDayId, ShiftType shiftType);
    List<Volunteering> findByEmployeeId(String employeeId);
    boolean existsByEmployeeIdAndSpecialDayId(String employeeId, String specialDayId);
}
