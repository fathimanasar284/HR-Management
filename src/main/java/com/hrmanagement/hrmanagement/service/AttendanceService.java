package com.hrmanagement.hrmanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrmanagement.hrmanagement.dao.AttendanceRepository;
import com.hrmanagement.hrmanagement.dao.EmployeeRepository;
import com.hrmanagement.hrmanagement.model.Attendance;
import com.hrmanagement.hrmanagement.model.Employee;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attRepo;
    private final EmployeeRepository empRepo;

    public AttendanceService(AttendanceRepository attRepo, EmployeeRepository empRepo) {
        this.attRepo = attRepo;
        this.empRepo = empRepo;
    }

    @Transactional
    public Attendance checkIn(Integer employeeId) {
        Employee emp = empRepo.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + employeeId));

        LocalDate today = LocalDate.now();

        Attendance att = attRepo.findByEmployee_IdAndWorkDate(employeeId, today)
                .orElseGet(() -> {
                    Attendance newAtt = new Attendance();
                    newAtt.setEmployee(emp);
                    newAtt.setWorkDate(today);
                    return newAtt;
                });

        if (att.getCheckInTime() == null) {
            att.setCheckInTime(LocalDateTime.now());
        }

        return attRepo.save(att);
    }

    @Transactional
    public Attendance checkOut(Integer employeeId) {
        LocalDate today = LocalDate.now();

        Attendance att = attRepo.findByEmployee_IdAndWorkDate(employeeId, today)
                .orElseThrow(() -> new IllegalArgumentException("Check-in record not found for today"));

        att.setCheckOutTime(LocalDateTime.now());

        return attRepo.save(att);
    }

    public List<Attendance> history(Integer employeeId) {
        return attRepo.findByEmployee_Id(employeeId);
    }
}
