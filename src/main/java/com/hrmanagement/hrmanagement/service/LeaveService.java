package com.hrmanagement.hrmanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hrmanagement.hrmanagement.dao.*;
import com.hrmanagement.hrmanagement.model.*;
import com.hrmanagement.hrmanagement.model.LeaveRequest.LeaveStatus;
import com.hrmanagement.hrmanagement.service.AttendanceService;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeaveService {
    private final LeaveRequestRepository leaveRepo;
    private final EmployeeRepository empRepo;

    public LeaveService(LeaveRequestRepository l, EmployeeRepository e){
        this.leaveRepo=l; this.empRepo=e;
    }

    @Transactional
    public LeaveRequest apply(Integer employeeId, LocalDate start, LocalDate end, String reason){
        Employee emp = empRepo.findById(employeeId).orElseThrow();
        LeaveRequest lr = new LeaveRequest();
        lr.setEmployee(emp);
        lr.setStartDate(start);
        lr.setEndDate(end);
        lr.setReason(reason);
        lr.setStatus(LeaveStatus.PENDING);
        return leaveRepo.save(lr);
    }

    @Transactional
    public LeaveRequest decide(Integer leaveId, Integer approverId, LeaveStatus status){
        LeaveRequest lr = leaveRepo.findById(leaveId).orElseThrow();
        Employee approver = empRepo.findById(approverId).orElseThrow();
        lr.setApprover(approver);
        lr.setStatus(status);
        return leaveRepo.save(lr);
    }

    public List<LeaveRequest> forEmployee(Integer empId){ return leaveRepo.findByEmployee_Id(empId); }
    public List<LeaveRequest> pending(){ return leaveRepo.findByStatus(LeaveStatus.PENDING); }
}
