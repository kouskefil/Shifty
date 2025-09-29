package com.kouskefil.employee.requests;

public record DepartmentRequest(
        String name,
        String shortName,
        String description
) {
}
