package com.kouskefil.planning.services; // <-- mets le bon package de ton projet

import com.kouskefil.planning.DTOs.DailyDTO;
import com.kouskefil.planning.DTOs.ShiftDTO;
import com.kouskefil.planning.DTOs.weeklyDTO;
import com.kouskefil.planning.models.Weekly;
import com.kouskefil.planning.repositories.WeeklyRepository;
import com.kouskefil.planning.services.WeeklyServiceImpl;
import com.kouskefil.planning.utils.DayOfWeek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeeklyServiceImplTest {

    @Mock
    private WeeklyRepository weeklyRepository;

    @InjectMocks
    private WeeklyServiceImpl service; // <-- pas l'interface WeeklyService

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

    @Test
    void addOrUpdateDaily_shouldInsertPauseAtMidpoint_andGenerateIds_andSortShifts() {
        DailyDTO sunday = new DailyDTO(
                DayOfWeek.SUNDAY,
                List.of(
                        new ShiftDTO(null, LocalTime.of(8, 0),  LocalTime.of(12, 0), "Caisse 1"),
                        new ShiftDTO(null, LocalTime.of(14, 0), LocalTime.of(18, 0), "Libre Service")
                )
        );

        when(weeklyRepository.findById("WEEKLY-1")).thenReturn(Optional.of(existingWeekly));
        ArgumentCaptor<Weekly> captor = ArgumentCaptor.forClass(Weekly.class);
        when(weeklyRepository.save(captor.capture())).thenAnswer(inv -> inv.getArgument(0));

        weeklyDTO result = service.addOrUpdateDaily("WEEKLY-1", sunday);

        verify(weeklyRepository).save(any(Weekly.class));

        Weekly saved = captor.getValue();
        assertThat(saved.getDailies()).hasSize(1);

        DailyDTO savedSunday = saved.getDailies().get(0);
        assertThat(savedSunday.dayOfWeek()).isEqualTo(DayOfWeek.SUNDAY);
        assertThat(savedSunday.shifts()).hasSize(3); // 2 shifts + 1 pause
        assertThat(savedSunday.shifts()).allSatisfy(s -> assertThat(s.internalId()).isNotBlank());

        // pause de 24 min à 12:00
        var pause = savedSunday.shifts().stream().filter(s -> "pause".equalsIgnoreCase(s.position())).findFirst().orElseThrow();
        assertThat(pause.startTime()).isEqualTo(LocalTime.of(12, 0));
        assertThat(pause.endTime()).isEqualTo(LocalTime.of(12, 24));

        // tri par startTime
        assertThat(savedSunday.shifts().stream().map(ShiftDTO::startTime).toList()).isSorted();

        assertThat(result.dailies()).hasSize(1);
    }
}