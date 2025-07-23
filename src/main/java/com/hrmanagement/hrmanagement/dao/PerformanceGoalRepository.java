package com.hrmanagement.hrmanagement.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hrmanagement.hrmanagement.model.*;
import java.util.List;

public interface PerformanceGoalRepository extends JpaRepository<PerformanceGoal,Integer> {
    List<PerformanceGoal> findByEmployee_Id(Integer employeeId);
}
