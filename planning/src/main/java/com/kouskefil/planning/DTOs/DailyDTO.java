package com.kouskefil.planning.DTOs;

import com.kouskefil.planning.utils.DayOfWeek;

import java.util.List;

public record DailyDTO(
         DayOfWeek dayOfWeek,
         List<ShiftDTO> shifts
) {
}
