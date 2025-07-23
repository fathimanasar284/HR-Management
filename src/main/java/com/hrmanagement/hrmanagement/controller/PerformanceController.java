package com.hrmanagement.hrmanagement.controller;

import org.springframework.web.bind.annotation.*;
import com.hrmanagement.hrmanagement.service.PerformanceService;
import com.hrmanagement.hrmanagement.model.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/performance")
public class PerformanceController {
    private final PerformanceService svc;
    public PerformanceController(PerformanceService s){this.svc=s;}

    @PostMapping("/goals")
    public PerformanceGoal createGoal(@RequestParam Integer employeeId,
                                      @RequestParam String title,
                                      @RequestParam String description,
                                      @RequestParam String targetDate){
        return svc.createGoal(employeeId,title,description,LocalDate.parse(targetDate));
    }

    @GetMapping("/goals/{employeeId}")
    public List<PerformanceGoal> goals(@PathVariable Integer employeeId){
        return svc.goalsForEmployee(employeeId);
    }

    @PostMapping("/reviews")
    public PerformanceReview review(@RequestParam Integer employeeId,
                                    @RequestParam(required=false) Integer reviewerId,
                                    @RequestParam Integer score,
                                    @RequestParam String comments){
        return svc.addReview(employeeId,reviewerId,score,comments);
    }

    @GetMapping("/reviews/{employeeId}")
    public List<PerformanceReview> reviews(@PathVariable Integer employeeId){
        return svc.reviewsForEmployee(employeeId);
    }
}
