package com.hrmanagement.hrmanagement.controller;

import org.springframework.web.bind.annotation.*;
import com.hrmanagement.hrmanagement.service.*;
import com.hrmanagement.hrmanagement.model.JobPosting;
import com.hrmanagement.hrmanagement.model.JobApplication;
import java.util.List;

@RestController
@RequestMapping("/api/recruitment")
public class RecruitmentController {
    private final RecruitmentService svc;
    public RecruitmentController(RecruitmentService s){this.svc=s;}

    @PostMapping("/post")
    public JobPosting postJob(@RequestBody JobPosting p){ return svc.savePosting(p); }

    @GetMapping("/posts")
    public List<JobPosting> posts(){ return svc.allPostings(); }

    @PostMapping("/apply")
    public JobApplication apply(@RequestParam Integer postingId,
                                @RequestParam String name,
                                @RequestParam String email,
                                @RequestParam(required=false) String resumeUrl){
        return svc.apply(postingId,name,email,resumeUrl);
    }

    @GetMapping("/applications/{postingId}")
    public List<JobApplication> apps(@PathVariable Integer postingId){
        return svc.forPosting(postingId);
    }
}
