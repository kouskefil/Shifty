package com.kouskefil.volunteering.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "special_day")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpecialDay {
    @Id
    private String id;
    private LocalDate date;
    private String description;
    private boolean active;
}
