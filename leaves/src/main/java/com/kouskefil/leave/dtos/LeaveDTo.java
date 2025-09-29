package com.kouskefil.leave.dtos;

import com.kouskefil.leave.utils.LeaveType;

import java.time.LocalDate;

public record LeaveDTo(
        String employeeId,
        LocalDate startDate,
        LocalDate endDate,
        LeaveType type,
        String reason

) {
}
