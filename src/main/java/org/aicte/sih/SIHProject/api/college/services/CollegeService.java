package org.aicte.sih.SIHProject.api.college.services;

import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.api.college.dto.request.CollegeRegistrationRequest;
import org.aicte.sih.SIHProject.api.jobs.dto.Entity.JobPost;
import org.aicte.sih.SIHProject.commons.APIResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface CollegeService {

    public List<CollegeEntity> getRegisteredColleges();
    public CollegeEntity registerCollege(CollegeRegistrationRequest collegeDetails);
    public CollegeEntity fetchCollege(Long id);
    void markCollegeClosed(Long id);
    Page<JobPost> fetchAllPostedJobs(String collegeUIN, PageRequest pageRequest);
    Page<JobPost> fetchAllClosedJobs(String collegeUIN, PageRequest pageRequest);
}
