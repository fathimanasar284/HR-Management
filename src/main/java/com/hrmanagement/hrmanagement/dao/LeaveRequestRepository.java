package com.hrmanagement.hrmanagement.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hrmanagement.hrmanagement.model.*;
import com.hrmanagement.hrmanagement.model.LeaveRequest.LeaveStatus;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Integer> {
    List<LeaveRequest> findByEmployee_Id(Integer employeeId);
    List<LeaveRequest> findByStatus(LeaveStatus status);
    List<LeaveRequest> findByApprover_Id(Integer approverId);
}
