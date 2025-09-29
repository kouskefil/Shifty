package com.kouskefil.volunteering.controllers;

import com.kouskefil.volunteering.DTOs.VolunteeringDTO;
import com.kouskefil.volunteering.Model.Volunteering;
import com.kouskefil.volunteering.service.VolunteeringService;
import com.kouskefil.volunteering.utls.ShiftType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteering")
@RequiredArgsConstructor
public class VolunteeringCtrl {
    private final VolunteeringService service;

    @PostMapping
    public ResponseEntity<Volunteering> save(@RequestBody VolunteeringDTO dto) {
        return  ResponseEntity.ok(service.save(dto));
    }
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Volunteering>> getByEmployee(@PathVariable String employeeId){
        return ResponseEntity.ok(service.getByEmployeeId(employeeId));
    }

    @GetMapping("/day/{specialDayId}")
    public ResponseEntity<List<Volunteering>> getBySpecialDay(@PathVariable String specialDayId){
        return ResponseEntity.ok(service.getAllForDay(specialDayId));
    }

    @GetMapping("/day/{specialDayId}/shift/{shiftType}")
    public ResponseEntity<List<Volunteering>> getByDayAndShift(
            @PathVariable String specialDayId,
            @PathVariable ShiftType shiftType
    ) {
        return ResponseEntity.ok(service.getByDayAndShift(specialDayId, shiftType));
    }
}
