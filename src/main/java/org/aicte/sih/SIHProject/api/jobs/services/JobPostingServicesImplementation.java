package org.aicte.sih.SIHProject.api.jobs.services;

import org.aicte.sih.SIHProject.api.college.Exception.CollegeExceptions;
import org.aicte.sih.SIHProject.api.college.Repository.CollegeRepository;
import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.api.faculty.dao.FacultyRepository;
import org.aicte.sih.SIHProject.api.jobs.dao.AppliedJobRepository;
import org.aicte.sih.SIHProject.api.jobs.dao.JobPostingRepository;
import org.aicte.sih.SIHProject.api.jobs.dto.EmploymentType;
import org.aicte.sih.SIHProject.api.jobs.dto.Entity.AppliedJob;
import org.aicte.sih.SIHProject.api.jobs.dto.Entity.JobPosting;
import org.aicte.sih.SIHProject.api.jobs.dto.request.ApplyForJobRequest;
import org.aicte.sih.SIHProject.api.jobs.dto.request.JobPostRequest;
import org.aicte.sih.SIHProject.api.jobs.exceptions.IncorrectJobPostingValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JobPostingServicesImplementation implements JobPostingServices {

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Autowired
    private AppliedJobRepository appliedJobRepository;

    @Override
    public JobPosting addNewJobPost(JobPostRequest jobPostRequest) throws IncorrectJobPostingValues {
        CollegeEntity college = collegeRepository.findOneByUin(jobPostRequest.getUin());
        if (college == null) {
            throw new CollegeExceptions("College Not Found");
        } else {
            JobPosting jobPosting = new JobPosting();
            jobPosting.setHeading(jobPostRequest.getHeading());
            jobPosting.setDescription(jobPostRequest.getDescription());
            jobPosting.setSubjects(jobPostRequest.getSubjects());
            jobPosting.setCollegeName(college.getName());
            jobPosting.setCollege(college);
            jobPosting.setPostedOn(LocalDateTime.now());
            jobPosting.setLastEditedOn(LocalDateTime.now());
            jobPosting.setMinYearsExperienceRequired(jobPostRequest.getMinYearsExperienceRequired());
            jobPosting.setMaxYearsExperienceRequired(jobPostRequest.getMaxYearsExperienceRequired());
            jobPosting.setTotalViews(0);
            jobPosting.setTotalApplicants(0);
            switch (jobPostRequest.getRoleType()) {
                case "FULLTIME":
                    jobPosting.setEmploymentType(EmploymentType.FULLTIME);
                    break;
                case "PARTIME":
                    jobPosting.setEmploymentType(EmploymentType.PARTIME);
                    break;
                case "CONTRACT":
                    jobPosting.setEmploymentType(EmploymentType.CONTRACT);
                    break;
                case "PERMANENT":
                    jobPosting.setEmploymentType(EmploymentType.PERMANENT);
                    break;
                default:
                    throw new IncorrectJobPostingValues("Unidentified Role Type");
            }
            jobPosting.setOpen(true);
            return jobPostingRepository.save(jobPosting);
        }
    }

    @Override
    public Page<JobPosting> listAllJobPostings(PageRequest pageRequest) {
        return jobPostingRepository.findAll(pageRequest);
    }

    @Override
    public void applyForJobPost(ApplyForJobRequest request) {
        AppliedJob appliedJob = new AppliedJob();
        appliedJob.setAppliedOn(LocalDateTime.now());
        appliedJob.setAppliedPost(jobPostingRepository.findOneById(request.getJobPosId()));
        appliedJob.setFaculty(facultyRepository.findOneById(request.getFacultyId()));
        appliedJob.setCoverLetter(request.getCoverLetter());
        appliedJobRepository.save(appliedJob);
    }
}
