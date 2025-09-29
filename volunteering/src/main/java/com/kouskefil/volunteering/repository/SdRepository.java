package com.kouskefil.volunteering.repository;

import com.kouskefil.volunteering.Model.SpecialDay;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SdRepository extends MongoRepository<SpecialDay, String> {
    List<SpecialDay> findByActiveTrue();
}
