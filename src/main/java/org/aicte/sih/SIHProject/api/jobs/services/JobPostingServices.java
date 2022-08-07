package org.aicte.sih.SIHProject.api.jobs.services;

import org.aicte.sih.SIHProject.api.jobs.dto.Entity.JobPosting;
import org.aicte.sih.SIHProject.api.jobs.dto.request.JobPostRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface JobPostingServices {
    public JobPosting addNewJobPost(JobPostRequest jobPostRequest);
    public Page<JobPosting> listAllJobPostings(PageRequest pageRequest);
}
