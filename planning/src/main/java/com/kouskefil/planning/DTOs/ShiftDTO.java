package com.kouskefil.planning.DTOs;

import java.time.LocalTime;

public record ShiftDTO(
        String internalId,
        LocalTime startTime,
         LocalTime endTime,
         String position
) {
}
