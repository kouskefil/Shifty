package com.kouskefil.planning.models;

import com.kouskefil.planning.DTOs.DailyDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "schedule")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Weekly {
    @Id
    private String id;
    private String employeeId;
    private Integer weekNumber;
    private Integer year;
    private List<DailyDTO> dailies;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
