package com.kouskefil.employee.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "qualification")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Qualification {

    @Id
    String qualificationId;
    String qualificationShortName;
    String qualificationName;
    String qualificationDesc;
}
