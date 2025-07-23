package com.hrmanagement.hrmanagement.controller;

import org.springframework.web.bind.annotation.*;
import com.hrmanagement.hrmanagement.service.LeaveService;
import com.hrmanagement.hrmanagement.model.LeaveRequest;
import com.hrmanagement.hrmanagement.model.LeaveRequest.LeaveStatus;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {
    private final LeaveService leaveService;
    public LeaveController(LeaveService l){this.leaveService=l;}

    @PostMapping("/apply")
    public LeaveRequest apply(@RequestParam Integer employeeId,
                              @RequestParam String startDate,
                              @RequestParam String endDate,
                              @RequestParam String reason){
        return leaveService.apply(employeeId,
                LocalDate.parse(startDate),
                LocalDate.parse(endDate),
                reason);
    }

    @GetMapping("/employee/{employeeId}")
    public List<LeaveRequest> employeeLeaves(@PathVariable Integer employeeId){
        return leaveService.forEmployee(employeeId);
    }

    @GetMapping("/pending")
    public List<LeaveRequest> pending(){ return leaveService.pending(); }

    @PostMapping("/{leaveId}/decide")
    public LeaveRequest decide(@PathVariable Integer leaveId,
                               @RequestParam Integer approverId,
                               @RequestParam LeaveStatus status){
        return leaveService.decide(leaveId,approverId,status);
    }
}
