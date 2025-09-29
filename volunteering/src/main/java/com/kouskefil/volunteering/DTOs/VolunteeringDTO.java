package com.kouskefil.volunteering.DTOs;

import com.kouskefil.volunteering.utls.ShiftType;

import java.util.List;

public record VolunteeringDTO(
        String employeeId,
        String specialDayId,
        List<ShiftType> selectedShifts
) {
}