package com.kouskefil.employee.repositories;

import com.kouskefil.employee.models.Qualification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QualificationRepository extends MongoRepository<Qualification, String> {
}
