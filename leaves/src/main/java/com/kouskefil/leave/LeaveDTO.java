package com.kouskefil.leave;

import com.kouskefil.leave.utils.LeaveType;

import java.time.LocalDate;

public record LeaveDTO (
        String employeeId,
        LocalDate startDate,
        LocalDate endDate,
        LeaveType type,
        String reason
){

}
