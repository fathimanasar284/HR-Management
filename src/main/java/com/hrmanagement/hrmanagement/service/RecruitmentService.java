package com.hrmanagement.hrmanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hrmanagement.hrmanagement.dao.*;
import com.hrmanagement.hrmanagement.model.*;

import java.util.List;

@Service
public class RecruitmentService {
    private final JobPostingRepository postRepo;
    private final JobApplicationRepository appRepo;

    public RecruitmentService(JobPostingRepository p, JobApplicationRepository a){
        this.postRepo=p; this.appRepo=a;
    }

    // Post jobs
    @Transactional public JobPosting savePosting(JobPosting p){ return postRepo.save(p); }
    public List<JobPosting> allPostings(){ return postRepo.findAll(); }

    // Apply
    @Transactional
    public JobApplication apply(Integer postingId, String name, String email, String resumeUrl){
        JobPosting posting = postRepo.findById(postingId).orElseThrow();
        JobApplication app = new JobApplication();
        app.setPosting(posting);
        app.setApplicantName(name);
        app.setEmail(email);
        app.setResumeUrl(resumeUrl);
        return appRepo.save(app);
    }

    public List<JobApplication> forPosting(Integer postingId){
        return appRepo.findByPosting_Id(postingId);
    }
}
