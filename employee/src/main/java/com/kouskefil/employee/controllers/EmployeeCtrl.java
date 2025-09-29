package com.kouskefil.employee.controllers;

import com.kouskefil.employee.dtos.EmployeeDTO;
import com.kouskefil.employee.requests.EmployeeRegRequest;
import com.kouskefil.employee.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employees")
public class EmployeeCtrl {

   private final EmployeeService employeeService;

    @PostMapping
    public void registerEmployee(@RequestBody EmployeeRegRequest employeeRegRequest) {
//        log.info("new employee registered {}", employeeRegRequest);
        employeeService.registerEmployee(employeeRegRequest);
    }
    public void editEmployee() {}

    public void deleteEmployee() {}

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/batch")
    public List<EmployeeDTO> createEmployeesBatch(@RequestBody List<EmployeeDTO> employees) {
        return employeeService.importEmployees(employees);
    }


}
