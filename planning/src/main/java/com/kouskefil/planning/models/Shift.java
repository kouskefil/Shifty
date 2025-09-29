package com.kouskefil.planning.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shift {

    @Builder.Default
    private String internalId = UUID.randomUUID().toString();
    private LocalTime startTime;
    private LocalTime endTime;
    private String position;
}
