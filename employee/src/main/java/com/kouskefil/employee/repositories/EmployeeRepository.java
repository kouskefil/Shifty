package com.kouskefil.employee.repositories;

import com.kouskefil.employee.models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, Long> {

}
