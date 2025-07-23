package com.hrmanagement.hrmanagement.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hrmanagement.hrmanagement.model.*;
import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Integer> {
    List<JobApplication> findByPosting_Id(Integer postingId);
}
