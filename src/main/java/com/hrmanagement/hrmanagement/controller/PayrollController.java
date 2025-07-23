package com.hrmanagement.hrmanagement.controller;

import org.springframework.web.bind.annotation.*;
import com.hrmanagement.hrmanagement.service.PayrollService;
import com.hrmanagement.hrmanagement.model.PayrollRecord;
import java.util.List;

@RestController
@RequestMapping("/api/payroll")
public class PayrollController {
    private final PayrollService payrollService;
    public PayrollController(PayrollService p){this.payrollService=p;}

    @PostMapping("/generate")
    public PayrollRecord generate(@RequestParam Integer employeeId,
                                  @RequestParam int year,
                                  @RequestParam int month){
        return payrollService.generate(employeeId,year,month);
    }

    @GetMapping("/{employeeId}")
    public List<PayrollRecord> history(@PathVariable Integer employeeId){
        return payrollService.forEmployee(employeeId);
    }
}
