package com.kouskefil.planning.models;

import com.kouskefil.planning.utils.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Daily {

    private DayOfWeek dayOfWeek;
    private List<Shift> shifts;
}
