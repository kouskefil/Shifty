package com.kouskefil.planning.services;

import com.kouskefil.planning.DTOs.DailyDTO;
import com.kouskefil.planning.DTOs.ShiftDTO;
import com.kouskefil.planning.DTOs.weeklyDTO;
import com.kouskefil.planning.utils.DayOfWeek;

import java.util.List;


public interface WeeklyService {
    weeklyDTO registerWeekly    (weeklyDTO dto);
    weeklyDTO getByEmployeeAndWeek(String employeeId, Integer weekNumber, Integer year);
    List<weeklyDTO> getAllForEmployee(String employeeId);

    weeklyDTO addOrUpdateDaily(String weeklyId, DailyDTO daily);

    weeklyDTO removeDaily(String weeklyId, DayOfWeek dayOfWeek);

    weeklyDTO addShiftToDay(String weeklyId, DayOfWeek dayOfWeek, ShiftDTO shift);

    weeklyDTO removeShiftFromDay(String weeklyId, DayOfWeek dayOfWeek, String shiftInternalId);

    weeklyDTO updateShiftInDay(String weeklyId, DayOfWeek dayOfWeek, ShiftDTO updatedShift);

    DailyDTO getDaily(String weeklyId, DayOfWeek dayOfWeek);

    List<ShiftDTO> getShiftsForDay(String weeklyId, DayOfWeek dayOfWeek);

    ShiftDTO getShiftById(String weeklyId, DayOfWeek dayOfWeek, String shiftInternalId);
}
