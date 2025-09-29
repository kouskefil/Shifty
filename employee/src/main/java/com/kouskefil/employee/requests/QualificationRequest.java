package com.kouskefil.employee.requests;

public record QualificationRequest(
        String qualificationShortName,
        String qualificationName,
        String qualificationDesc
) {
}
