package com.kouskefil.volunteering.controllers;

import com.kouskefil.volunteering.DTOs.SpecialDayDTO;
import com.kouskefil.volunteering.Model.SpecialDay;
import com.kouskefil.volunteering.service.SpecialDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/special-days")
@RequiredArgsConstructor
public class SpecialDayCtrl {
    private final SpecialDayService service;

    @PostMapping
    public ResponseEntity<SpecialDay> createSpecialDay(@RequestBody SpecialDayDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("active")
    public ResponseEntity<List<SpecialDay>> getActiveSpecialDays() {
        return ResponseEntity.ok(service.getActiveDays());
    }

    @GetMapping
    public ResponseEntity<List<SpecialDay>> getAllSpecialDays() {
        return ResponseEntity.ok(service.getAll());
    }
}
