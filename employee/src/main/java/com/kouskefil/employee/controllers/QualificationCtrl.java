package com.kouskefil.employee.controllers;

import com.kouskefil.employee.models.Qualification;
import com.kouskefil.employee.requests.QualificationRequest;
import com.kouskefil.employee.services.QualificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/qualifications")
public class QualificationCtrl {

    private final QualificationService qualificationService;

    @PostMapping
    public Qualification registerQualification(@RequestBody QualificationRequest request) {
        return qualificationService.register(request);
    }
}
