package com.kouskefil.planning.service;

import com.kouskefil.planning.models.Weekly;
import com.kouskefil.planning.repository.WeeklyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class WeeklyServiceImplTest {
    @Mock
    private WeeklyRepository weeklyRepository;

    @InjectMocks
    private WeeklyService weeklyService;

    private Weekly existingWeekly;

    @BeforeEach
    void setUp() {
        existingWeekly = Weekly.builder()
                .id("WEEKLY-1")
                .employeeId("cab25dd6-e5dc-48fd-bdd8-289d009d5b10")
                .weekNumber(32)
                .year(2025)
                .dailies(new ArrayList<>())
                .createdAt(LocalDateTime.now().minusDays(1))
                .updatedAt(LocalDateTime.now().minusDays(1))
                .build();

    }
}
