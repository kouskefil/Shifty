package com.kouskefil.volunteering.DTOs;

import java.time.LocalDate;

public record SpecialDayDTO(
        LocalDate date,
        String description,
        boolean active
) {
}
