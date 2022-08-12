package org.aicte.sih.SIHProject.api.jobs.services;

import org.aicte.sih.SIHProject.api.jobs.dto.Entity.JobPost;
import org.aicte.sih.SIHProject.api.jobs.dto.request.ApplyForJobRequest;
import org.aicte.sih.SIHProject.api.jobs.dto.request.JobPostRequest;
import org.aicte.sih.SIHProject.api.jobs.exceptions.IncorrectJobPostingValues;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface JobPostingServices {
    public JobPost addNewJobPost(JobPostRequest jobPostRequest) throws IncorrectJobPostingValues;
    public Page<JobPost> listAllJobPostings(PageRequest pageRequest);
    public void applyForJobPost(ApplyForJobRequest request);
}
