package com.kouskefil.leave;

import com.kouskefil.leave.models.Leave;
import com.kouskefil.leave.services.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveCtrl {
    private final LeaveService service;

    @PostMapping
    public ResponseEntity <Leave> register(@RequestBody  LeaveDTO dto) {
        return ResponseEntity.ok(service.createLeave(dto));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List <Leave>> getLeaves(@PathVariable String employeeId) {
        return ResponseEntity.ok(service.getLeavesByEmployee(employeeId));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity <Leave> approve(@PathVariable String id) {
        return ResponseEntity.ok(service.approveLeave(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity <Leave> reject(@PathVariable String id) {
        return ResponseEntity.ok(service.rejectLeave(id));
    }
}
