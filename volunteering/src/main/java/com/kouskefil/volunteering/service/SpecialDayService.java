package com.kouskefil.volunteering.service;

import com.kouskefil.volunteering.DTOs.SpecialDayDTO;
import com.kouskefil.volunteering.Model.SpecialDay;
import com.kouskefil.volunteering.repository.SdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialDayService {
    private final SdRepository repository;

    public SpecialDay save(SpecialDayDTO dto) {
        SpecialDay specialDay = SpecialDay.builder()
                .date(dto.date())
                .description(dto.description())
                .active(dto.active())
                .build();
        return repository.save(specialDay);
    }
    public List<SpecialDay> getActiveDays() {
        return repository.findByActiveTrue();
    }

    public List<SpecialDay> getAll() {
        return repository.findAll();
    }
}
