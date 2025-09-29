package com.kouskefil.employee.repositories;

import com.kouskefil.employee.models.Department;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartmentRepository extends MongoRepository<Department, Long> {

}
