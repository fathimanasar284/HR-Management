package com.hrmanagement.hrmanagement.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hrmanagement.hrmanagement.model.*;
public interface JobPostingRepository extends JpaRepository<JobPosting,Integer> {}
