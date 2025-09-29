package com.kouskefil.leave.services;

import com.kouskefil.leave.LeaveDTO;
import com.kouskefil.leave.LeaveRepository;
import com.kouskefil.leave.models.Leave;
import com.kouskefil.leave.utils.LeaveStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveService {
    private final LeaveRepository repository;

    public Leave createLeave(LeaveDTO dto){
        Leave leave = Leave.builder()
                .employeeId(dto.employeeId())
                .startDate(dto.startDate())
                .endDate(dto.endDate())
                .type(dto.type())
                .reason(dto.reason())
                .status(LeaveStatus.PENDING)
                .requestDate(LocalDate.now())
                .build();
        return repository.save(leave);
    }

    public List<Leave> getLeavesByEmployee(String employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    public Leave approveLeave(String id) {
        Leave leave = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
        leave.setStatus(LeaveStatus.APPROVED);
        return repository.save(leave);
    }

    public Leave rejectLeave(String id) {
        Leave leave = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
        leave.setStatus(LeaveStatus.REJECTED);
        return repository.save(leave);
    }
}
