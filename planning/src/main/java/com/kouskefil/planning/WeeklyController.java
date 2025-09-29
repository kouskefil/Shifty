package com.kouskefil.planning;

import com.kouskefil.planning.DTOs.weeklyDTO;
import com.kouskefil.planning.service.WeeklyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planning")
@RequiredArgsConstructor
public class WeeklyController {
  private final WeeklyService weeklyService;


  @PostMapping
  public weeklyDTO createPlanning(@RequestBody weeklyDTO dto) {
      return weeklyService.registerWeekly(dto);
  }

  @GetMapping("/{employeeId}/{weekNumber}/{year}")
 public weeklyDTO getPlanningForWeek(
         @PathVariable String employeeId,
         @PathVariable Integer weekNumber,
         @PathVariable Integer year
 ) {
      return weeklyService.getByEmployeeAndWeek(employeeId, weekNumber, year);
 }

 @GetMapping("/{employeeId}")
 public List<weeklyDTO> getAllPlanningForWeek(@PathVariable String employeeId) {
      return weeklyService.getAllForEmployee(employeeId);
 }

 //CRUD sur Shift & Daily
  //  @PutMapping("/{id}/day/{day}/shift")

}
