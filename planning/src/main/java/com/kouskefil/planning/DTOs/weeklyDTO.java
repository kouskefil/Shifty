package com.kouskefil.planning.DTOs;

import java.util.List;

public record weeklyDTO(
        int weekNumber,
        int year,
        String employeeId,
        List<DailyDTO> dailies
) {
}
