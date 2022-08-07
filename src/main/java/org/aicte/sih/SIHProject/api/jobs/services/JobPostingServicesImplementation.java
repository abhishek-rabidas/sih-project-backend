package org.aicte.sih.SIHProject.api.jobs.services;

import org.aicte.sih.SIHProject.api.jobs.dto.Entity.JobPosting;
import org.aicte.sih.SIHProject.api.jobs.dto.request.JobPostRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class JobPostingServicesImplementation implements JobPostingServices{
    @Override
    public JobPosting addNewJobPost(JobPostRequest jobPostRequest) {
        return null;
    }

    @Override
    public Page<JobPosting> listAllJobPostings(PageRequest pageRequest) {
        return null;
    }
}
