package com.hrmanagement.hrmanagement.controller;

import org.springframework.web.bind.annotation.*;
import com.hrmanagement.hrmanagement.service.AttendanceService;
import com.hrmanagement.hrmanagement.model.Attendance;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    private final AttendanceService attService;
    public AttendanceController(AttendanceService a){this.attService=a;}

    @PostMapping("/checkin")
    public Attendance checkIn(@RequestParam Integer employeeId){
        return attService.checkIn(employeeId);
    }

    @PostMapping("/checkout")
    public Attendance checkOut(@RequestParam Integer employeeId){
        return attService.checkOut(employeeId);
    }

    @GetMapping("/{employeeId}")
    public List<Attendance> history(@PathVariable Integer employeeId){
        return attService.history(employeeId);
    }
}
