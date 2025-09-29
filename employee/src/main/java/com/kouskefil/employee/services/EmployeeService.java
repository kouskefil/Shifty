package com.kouskefil.employee.services;

import com.kouskefil.employee.dtos.EmployeeDTO;
import com.kouskefil.employee.models.Employee;
import com.kouskefil.employee.repositories.EmployeeRepository;
import com.kouskefil.employee.repositories.QualificationRepository;
import com.kouskefil.employee.requests.EmployeeRegRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final  EmployeeRepository employeeRepository;
    private final QualificationRepository qualificationRepository;

    public void registerEmployee(EmployeeRegRequest employeeRegRequest){
        Employee employee = Employee.builder()
                .fname(employeeRegRequest.fname())
                .lname(employeeRegRequest.lname())
                .email(employeeRegRequest.email())
                .phone(employeeRegRequest.phone())
                .address(employeeRegRequest.address())
                .departmentIds(employeeRegRequest.departementIds())
                .qualificationIds(employeeRegRequest.qualificationIds())
                .build();
        employeeRepository.save(employee);

    }

    public List<EmployeeDTO> importEmployees(List<EmployeeDTO> dtos) {
        List<Employee> entities = dtos.stream().map(
                d -> Employee.builder()
                        .id(UUID.randomUUID().toString())
                        .fname(d.fname())
                        .lname(d.lname())
                        .email(d.email())
                        .phone(d.phone())
                        .address(d.address())
                        .active(d.active())
                        .departmentIds(d.departmentIds())
                        .qualificationIds(d.qualificationIds())
                        .build()
        ).toList();

        List<Employee> saved = employeeRepository.saveAll(entities);
        // map Entity -> DTO (toujours sans mapper dédié)
        return saved.stream().map(e ->
                new EmployeeDTO(
                        e.getId(),
                        e.getFname(),
                        e.getLname(),
                        e.getEmail(),
                        e.getPhone(),
                        e.getAddress(),
                        e.isActive(),
                        e.getDepartmentIds(),
                        e.getQualificationIds()
                )
        ).toList();
    }

    public List<EmployeeDTO> getAllEmployees(){
        return employeeRepository.findAll()
                .stream()
                .map(e -> new EmployeeDTO(
                        e.getId(),
                        e.getLname(),
                        e.getFname(),
                        e.getEmail(),
                        e.getPhone(),
                        e.getAddress(),
                        e.isActive(),
                        e.getDepartmentIds(),
                        e.getQualificationIds()
                ))
                .toList();
    }
}
