package com.kouskefil.volunteering.Model;

import com.kouskefil.volunteering.utls.ShiftType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "volunteering")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Volunteering {
    @Id
    private String id;
    private String employeeId;
    private String specialDayId;
    private List<ShiftType> selectedShifts;
    private LocalDateTime submittedAt;
}
