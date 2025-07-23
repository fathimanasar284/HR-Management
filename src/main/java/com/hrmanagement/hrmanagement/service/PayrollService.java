package com.hrmanagement.hrmanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hrmanagement.hrmanagement.dao.*;
import com.hrmanagement.hrmanagement.model.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class PayrollService {
    private final PayrollRecordRepository payrollRepo;
    private final EmployeeRepository empRepo;
    private final AttendanceRepository attRepo;

    public PayrollService(PayrollRecordRepository p, EmployeeRepository e, AttendanceRepository a){
        this.payrollRepo=p; this.empRepo=e; this.attRepo=a;
    }

    /**
     * Simple calculation:
     *   dailyRate = baseSalary / workingDaysInMonth (assume 22)
     *   daysPresent = count of attendance rows with checkIn
     *   gross = baseSalary
     *   deductions = (22 - daysPresent) * dailyRate
     *   net = gross - deductions
     */
    @Transactional
    public PayrollRecord generate(Integer employeeId, int year, int month){
        Employee emp = empRepo.findById(employeeId).orElseThrow();
        YearMonth ym = YearMonth.of(year, month);
        int workingDays = 22; // simple assumption
        long daysPresent = attRepo.findByEmployee_Id(employeeId).stream()
            .filter(a -> a.getWorkDate().getYear()==year && a.getWorkDate().getMonthValue()==month)
            .filter(a -> a.getCheckInTime()!=null)
            .count();
        double base = emp.getBaseSalary()!=null?emp.getBaseSalary():0.0;
        double dailyRate = base / workingDays;
        double deductions = (workingDays - daysPresent) * dailyRate;
        double net = base - deductions;

        PayrollRecord pr = payrollRepo.findByEmployee_IdAndYearAndMonth(employeeId,year,month)
           .orElseGet(PayrollRecord::new);

        pr.setEmployee(emp);
        pr.setYear(year);
        pr.setMonth(month);
        pr.setGrossSalary(base);
        pr.setDeductions(deductions);
        pr.setNetSalary(net);
        return payrollRepo.save(pr);
    }

    public List<PayrollRecord> forEmployee(Integer employeeId){
        return payrollRepo.findByEmployee_Id(employeeId);
    }
}
