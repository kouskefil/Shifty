package com.kouskefil.leave.models;

import com.kouskefil.leave.utils.LeaveStatus;
import com.kouskefil.leave.utils.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "leave")
public class Leave {
    @Id
    private String id;
    private String employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveType type;
    private LeaveStatus status;
    private String reason;
    private LocalDate requestDate;
}
