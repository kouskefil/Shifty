package com.kouskefil.employee.services;


import com.kouskefil.employee.models.Qualification;
import com.kouskefil.employee.repositories.QualificationRepository;
import com.kouskefil.employee.requests.QualificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QualificationService {
    private final QualificationRepository qualificationRepository;

    public Qualification register(QualificationRequest request) {
        Qualification qualification = Qualification.builder()
                .qualificationShortName(request.qualificationShortName())
                .qualificationName(request.qualificationName())
                .qualificationDesc(request.qualificationDesc())
                .build();
        return qualificationRepository.save(qualification);
    }
}
