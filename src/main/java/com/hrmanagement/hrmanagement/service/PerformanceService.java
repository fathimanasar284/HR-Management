package com.hrmanagement.hrmanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hrmanagement.hrmanagement.dao.*;
import com.hrmanagement.hrmanagement.model.*;

import java.time.LocalDate;
import java.util.List;

@Service
public class PerformanceService {
    private final PerformanceGoalRepository goalRepo;
    private final PerformanceReviewRepository reviewRepo;
    private final EmployeeRepository empRepo;

    public PerformanceService(PerformanceGoalRepository g, PerformanceReviewRepository r, EmployeeRepository e){
        this.goalRepo=g; this.reviewRepo=r; this.empRepo=e;
    }

    @Transactional
    public PerformanceGoal createGoal(Integer empId, String title, String desc, LocalDate target){
        Employee emp = empRepo.findById(empId).orElseThrow();
        PerformanceGoal goal = new PerformanceGoal();
        goal.setEmployee(emp);
        goal.setTitle(title);
        goal.setDescription(desc);
        goal.setTargetDate(target);
        return goalRepo.save(goal);
    }

    public List<PerformanceGoal> goalsForEmployee(Integer empId){ return goalRepo.findByEmployee_Id(empId); }

    @Transactional
    public PerformanceReview addReview(Integer empId, Integer reviewerId, Integer score, String comments){
        Employee emp = empRepo.findById(empId).orElseThrow();
        Employee reviewer = reviewerId!=null?empRepo.findById(reviewerId).orElse(null):null;
        PerformanceReview pr = new PerformanceReview();
        pr.setEmployee(emp);
        pr.setReviewer(reviewer);
        pr.setScore(score);
        pr.setComments(comments);
        return reviewRepo.save(pr);
    }

    public List<PerformanceReview> reviewsForEmployee(Integer empId){ return reviewRepo.findByEmployee_Id(empId); }
}
