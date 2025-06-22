package com.kouskefil.planning.controllers;

import com.kouskefil.planning.requests.EmployeeRegRequest;
import com.kouskefil.planning.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
