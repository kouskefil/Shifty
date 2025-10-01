package com.kouskefil.planning.repositories;

import com.kouskefil.planning.models.Weekly;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface WeeklyRepository extends MongoRepository<Weekly, String> {

    Optional<Weekly> findByEmployeeIdAndWeekNumberAndYear(String employeeId, Integer weekNumber, Integer year);
    List<Weekly> findByWeekNumber(Integer weekNumber);
    List<Weekly> findByEmployeeId(String employeeId);

    Optional<Weekly> findById(String s);
}
