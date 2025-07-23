package com.hrmanagement.hrmanagement.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hrmanagement.hrmanagement.model.*;
import java.util.List;

public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview,Integer> {
    List<PerformanceReview> findByEmployee_Id(Integer employeeId);
    List<PerformanceReview> findByReviewer_Id(Integer reviewerId);
}
