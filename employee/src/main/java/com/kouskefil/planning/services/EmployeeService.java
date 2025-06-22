package com.kouskefil.planning.services;

import com.kouskefil.planning.models.Employee;
import com.kouskefil.planning.repositories.EmployeeRepository;
import com.kouskefil.planning.requests.EmployeeRegRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final  EmployeeRepository employeeRepository;

    public void registerEmployee(EmployeeRegRequest employeeRegRequest){
        Employee employee = Employee.builder()
                .fname(employeeRegRequest.fname())
                .lname(employeeRegRequest.lname())
                .email(employeeRegRequest.email())
                .phone(employeeRegRequest.phone())
                .address(employeeRegRequest.address())
                .departmentId(employeeRegRequest.dptId())
                .qualificationId(employeeRegRequest.qualificationId())
                .build();
        employeeRepository.save(employee);

    }
}
