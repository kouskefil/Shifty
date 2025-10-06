package com.kouskefil.planning;

import com.kouskefil.planning.DTOs.weeklyDTO;
import com.kouskefil.planning.services.WeeklyService;
import com.kouskefil.planning.services.WeeklyServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/planning")
@RequiredArgsConstructor
public class WeeklyController {
  private final WeeklyService weeklyService;


  @PostMapping
  public weeklyDTO createPlanning(@RequestBody weeklyDTO dto) {
      log.info("Planning Created {}", dto);
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
