package com.kouskefil.planning.requests;

import java.util.List;

public record EmployeeRegRequest(
        String fname,
        String lname,
        String email,
        String phone,
        String address,
        List <Long> dptId,
        List <Long> qualificationId
) {
}
