package com.kouskefil.employee.services;

import com.kouskefil.employee.models.Department;
import com.kouskefil.employee.repositories.DepartmentRepository;
import com.kouskefil.employee.requests.DepartmentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department register(DepartmentRequest department) {
        Department newDepartment = Department.builder()
                .name(department.name())
                .shortName(department.shortName())
                .description(department.description())
                .build();
        return departmentRepository.save(newDepartment);
    }
}
