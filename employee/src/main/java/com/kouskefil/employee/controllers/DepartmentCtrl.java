package com.kouskefil.employee.controllers;

import com.kouskefil.employee.models.Department;
import com.kouskefil.employee.requests.DepartmentRequest;
import com.kouskefil.employee.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/departments")
public class DepartmentCtrl {
    private final DepartmentService departmentService;

    @PostMapping
    public Department register(@RequestBody DepartmentRequest departmentRequest) {
       return departmentService.register(departmentRequest);

    }
}
