package com.kouskefil.employee.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "employee")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @Builder.Default
    String id =  UUID.randomUUID().toString();
    String fname;
    String lname;
    String email;
    String phone;
    String address;
    boolean active;

    List <String> departmentIds;
    List <String> qualificationIds;
}
