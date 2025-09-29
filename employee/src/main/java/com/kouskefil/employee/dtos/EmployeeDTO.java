package com.kouskefil.employee.dtos;

import java.util.List;

public record EmployeeDTO(
        String EmployeeId,
        String fname,
        String lname,
        String email,
        String phone,
        String address,
        boolean active,
        List<String> departmentIds,
        List<String> qualificationIds
) {
}
