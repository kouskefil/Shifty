package com.kouskefil.planning.services;

import com.kouskefil.planning.DTOs.DailyDTO;
import com.kouskefil.planning.DTOs.ShiftDTO;
import com.kouskefil.planning.DTOs.weeklyDTO;
import com.kouskefil.planning.models.Weekly;
import com.kouskefil.planning.repositories.WeeklyRepository;
import com.kouskefil.planning.utils.DayOfWeek;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeeklyServiceImpl implements WeeklyService {


    private final WeeklyRepository repository;

    @Override
    public weeklyDTO registerWeekly(weeklyDTO dto) {
        List<DailyDTO> updatedDailies = dto.dailies().stream().map(daily -> {
            List<ShiftDTO> updatedShifts = daily.shifts().stream()
                    .map(shift -> {
                        if (shift.internalId() == null || shift.internalId().isBlank()) {
                            return new ShiftDTO(UUID.randomUUID().toString(), shift.startTime(), shift.endTime(), shift.position());
                        }
                        return shift;
                    })
                    .toList();
            return new DailyDTO(daily.dayOfWeek(), updatedShifts);
        }).toList();
        Weekly entity = Weekly.builder()
                .employeeId(dto.employeeId())
                .weekNumber(dto.weekNumber())
                .year(dto.year())
                .dailies(updatedDailies)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Weekly savedEntity = repository.save(entity);
        for (DailyDTO daily : dto.dailies()) {
            addOrUpdateDaily(savedEntity.getId(),daily);
        }
        Weekly updated = repository.findById(savedEntity.getId()).orElseThrow();

        return new weeklyDTO(
                updated.getWeekNumber(),
                updated.getYear(),
                updated.getEmployeeId(),
                updated.getDailies()
        );
    }

    @Override
    public weeklyDTO getByEmployeeAndWeek(String employeeId, Integer weekNumber, Integer year) {
        return repository.findByEmployeeIdAndWeekNumberAndYear(employeeId, weekNumber, year)
                .map(weekly -> new weeklyDTO(
                        weekly.getWeekNumber(),
                        weekly.getYear(),
                        weekly.getEmployeeId(),
                        weekly.getDailies()
                )).orElse(null);
    }

    @Override
    public List<weeklyDTO> getAllForEmployee(String employeeId) {
        return repository.findByEmployeeId(employeeId).stream()
                .map(w -> new weeklyDTO(
                        w.getWeekNumber(),
                        w.getYear(),
                        w.getEmployeeId(),
                        w.getDailies()
                ))
                .collect(Collectors.toList());
    }
    @Override
    public weeklyDTO addOrUpdateDaily(String weeklyId, DailyDTO daily) {
        Weekly weekly = repository.findById(weeklyId).orElseThrow();
        List<ShiftDTO> shifts = new ArrayList<>();
        for (ShiftDTO shift : daily.shifts()) {
            if (shift.internalId() == null || shift.internalId().isBlank()) {
                shift = new ShiftDTO(UUID.randomUUID().toString(), shift.startTime(), shift.endTime(), shift.position());
            }
            shifts.add(shift);
        }
        long totalWorkMinutes = shifts.stream()
                .mapToLong(s -> Duration.between(s.startTime(), s.endTime()).toMinutes())
                .sum();

        if (totalWorkMinutes >= 60) {
            long pauseMinutes = (totalWorkMinutes / 60) * 3;
            long halfPoint = totalWorkMinutes / 2;

            LocalTime pauseStart = calculateMidpoint(shifts, halfPoint);
            LocalTime pauseEnd = pauseStart.plusMinutes(pauseMinutes);
            shifts.add(new ShiftDTO(UUID.randomUUID().toString(), pauseStart, pauseEnd, "pause"));
            shifts.sort(Comparator.comparing(ShiftDTO::startTime));
        }
        List<DailyDTO> updatedDailies = weekly.getDailies().stream()
                .filter(d -> !d.dayOfWeek().equals(daily.dayOfWeek()))
                .collect(Collectors.toList());
        updatedDailies.add(new DailyDTO(daily.dayOfWeek(), shifts));
        weekly.setDailies(updatedDailies);
        weekly.setUpdatedAt(LocalDateTime.now());
        repository.save(weekly);
        return new weeklyDTO(weekly.getWeekNumber(), weekly.getYear(), weekly.getEmployeeId(), weekly.getDailies());
    }

    private LocalTime calculateMidpoint(List<ShiftDTO> shifts, long halfPoint) {
        long acc = 0;
        for (ShiftDTO shift : shifts) {
            long duration = Duration.between(shift.startTime(), shift.endTime()).toMinutes();
            if (acc + duration >= halfPoint) {
                return shift.startTime().plusMinutes(halfPoint - acc);
            }
            acc += duration;
        }
        return LocalTime.NOON;
    }

    @Override
    public weeklyDTO removeDaily(String weeklyId, DayOfWeek dayOfWeek) {
        Weekly weekly = repository.findById(weeklyId).orElseThrow();
        weekly.getDailies().removeIf(d -> d.dayOfWeek().equals(dayOfWeek));
        weekly.setUpdatedAt(LocalDateTime.now());
        repository.save(weekly);
        return new weeklyDTO(weekly.getWeekNumber(), weekly.getYear(), weekly.getEmployeeId(), weekly.getDailies());
    }

    @Override
    public weeklyDTO addShiftToDay(String weeklyId, DayOfWeek dayOfWeek, ShiftDTO shift) {
        Weekly weekly = repository.findById(weeklyId).orElseThrow();
        if (shift.internalId() == null || shift.internalId().isBlank()) {
            shift = new ShiftDTO(UUID.randomUUID().toString(), shift.startTime(), shift.endTime(), shift.position());
        }
        List<DailyDTO> dailies = weekly.getDailies();
        for (int i = 0; i < dailies.size(); i++) {
            DailyDTO d = dailies.get(i);
            if (d.dayOfWeek().equals(dayOfWeek)) {
                List<ShiftDTO> newShifts = new ArrayList<>(d.shifts());
                newShifts.add(shift);
                newShifts.sort(Comparator.comparing(ShiftDTO::startTime));
                dailies.set(i, new DailyDTO(dayOfWeek, newShifts));
                break;
            }
        }
        weekly.setDailies(dailies);
        weekly.setUpdatedAt(LocalDateTime.now());
        repository.save(weekly);
        return new weeklyDTO(weekly.getWeekNumber(), weekly.getYear(), weekly.getEmployeeId(), weekly.getDailies());
    }

    @Override
    public weeklyDTO removeShiftFromDay(String weeklyId, DayOfWeek dayOfWeek, String shiftInternalId) {
        Weekly weekly = repository.findById(weeklyId).orElseThrow();
        List<DailyDTO> updated = weekly.getDailies().stream().map(d -> {
            if (d.dayOfWeek().equals(dayOfWeek)) {
                List<ShiftDTO> shifts = d.shifts().stream()
                        .filter(s -> !s.internalId().equals(shiftInternalId))
                        .toList();
                return new DailyDTO(dayOfWeek, shifts);
            }
            return d;
        }).toList();
        weekly.setDailies(updated);
        weekly.setUpdatedAt(LocalDateTime.now());
        repository.save(weekly);
        return new weeklyDTO(weekly.getWeekNumber(), weekly.getYear(), weekly.getEmployeeId(), weekly.getDailies());
    }

    @Override
    public weeklyDTO updateShiftInDay(String weeklyId, DayOfWeek dayOfWeek, ShiftDTO updatedShift) {
        Weekly weekly = repository.findById(weeklyId).orElseThrow();
        List<DailyDTO> updated = weekly.getDailies().stream().map(d -> {
            if (d.dayOfWeek().equals(dayOfWeek)) {
                List<ShiftDTO> newShifts = d.shifts().stream()
                        .map(s -> s.internalId().equals(updatedShift.internalId()) ? updatedShift : s)
                        .sorted(Comparator.comparing(ShiftDTO::startTime))
                        .toList();
                return new DailyDTO(dayOfWeek, newShifts);
            }
            return d;
        }).toList();
        weekly.setDailies(updated);
        weekly.setUpdatedAt(LocalDateTime.now());
        repository.save(weekly);
        return new weeklyDTO(weekly.getWeekNumber(), weekly.getYear(), weekly.getEmployeeId(), weekly.getDailies());
    }

    @Override
    public DailyDTO getDaily(String weeklyId, DayOfWeek dayOfWeek) {
        Weekly weekly = repository.findById(weeklyId).orElseThrow();
        return weekly.getDailies().stream()
                .filter(d -> d.dayOfWeek().equals(dayOfWeek))
                .findFirst().orElse(null);
    }

    @Override
    public List<ShiftDTO> getShiftsForDay(String weeklyId, DayOfWeek dayOfWeek) {
        DailyDTO daily = getDaily(weeklyId, dayOfWeek);
        return daily != null ? daily.shifts() : List.of();
    }

    @Override
    public ShiftDTO getShiftById(String weeklyId, DayOfWeek dayOfWeek, String shiftInternalId) {
        return getShiftsForDay(weeklyId, dayOfWeek).stream()
                .filter(s -> s.internalId().equals(shiftInternalId))
                .findFirst().orElse(null);
    }

}
