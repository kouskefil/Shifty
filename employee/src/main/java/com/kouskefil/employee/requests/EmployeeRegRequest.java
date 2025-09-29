package com.kouskefil.employee.requests;

import java.util.List;

public record EmployeeRegRequest(
        String fname,
        String lname,
        String email,
        String phone,
        String address,
        List <String> departementIds,
        List <String> qualificationIds
) {
}
