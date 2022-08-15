package org.aicte.sih.SIHProject.api.jobs.services;

import org.aicte.sih.SIHProject.api.college.Exception.CollegeExceptions;
import org.aicte.sih.SIHProject.api.college.Repository.CollegeRepository;
import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.api.faculty.dao.FacultyRepository;
import org.aicte.sih.SIHProject.api.jobs.dao.AppliedJobRepository;
import org.aicte.sih.SIHProject.api.jobs.dao.JobPostingRepository;
import org.aicte.sih.SIHProject.api.jobs.dto.EmploymentType;
import org.aicte.sih.SIHProject.api.jobs.dto.Entity.AppliedJob;
import org.aicte.sih.SIHProject.api.jobs.dto.Entity.JobPost;
import org.aicte.sih.SIHProject.api.jobs.dto.Response.JobApplicationResponse;
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
    public JobPost addNewJobPost(JobPostRequest jobPostRequest) throws IncorrectJobPostingValues {
        CollegeEntity college = collegeRepository.findOneByUin(jobPostRequest.getCollegeUin());
        if (college == null) {
            throw new CollegeExceptions("College Not Found");
        } else {
            JobPost jobPost = new JobPost();
            jobPost.setHeading(jobPostRequest.getHeading());
            jobPost.setDescription(jobPostRequest.getDescription());
            jobPost.setSubjects(jobPostRequest.getSubjects());
            jobPost.setCollegeName(college.getName());
            jobPost.setCollege(college);
            jobPost.setCity(college.getCity());
            jobPost.setState(college.getState());
            jobPost.setPostedOn(LocalDateTime.now());
            jobPost.setLastEditedOn(LocalDateTime.now());
            jobPost.setMinYearsExperienceRequired(jobPostRequest.getMinYearsExperienceRequired());
            jobPost.setMaxYearsExperienceRequired(jobPostRequest.getMaxYearsExperienceRequired());
            jobPost.setTotalViews(0);
            jobPost.setTotalApplicants(0);
            switch (jobPostRequest.getRoleType()) {
                case "FULLTIME":
                    jobPost.setEmploymentType(EmploymentType.FULLTIME);
                    break;
                case "PARTIME":
                    jobPost.setEmploymentType(EmploymentType.PARTIME);
                    break;
                case "CONTRACT":
                    jobPost.setEmploymentType(EmploymentType.CONTRACT);
                    break;
                case "PERMANENT":
                    jobPost.setEmploymentType(EmploymentType.PERMANENT);
                    break;
                default:
                    throw new IncorrectJobPostingValues("Unidentified Role Type");
            }
            jobPost.setOpen(true);
            return jobPostingRepository.save(jobPost);
        }
    }

    @Override
    public Page<JobPost> listAllJobPostings(PageRequest pageRequest) {
        return jobPostingRepository.findAllByIsOpenTrue(pageRequest);
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

    @Override
    public JobPost editJobPost(JobPostRequest jobPostRequest, Long id) throws IncorrectJobPostingValues {
        CollegeEntity college = collegeRepository.findOneByUin(jobPostRequest.getCollegeUin());
        if (college == null) {
            throw new CollegeExceptions("College Not Found");
        } else {
            JobPost jobPost = jobPostingRepository.findOneById(id);
            if (jobPost == null) {
                throw new IncorrectJobPostingValues("Job Post not found");
            }
            jobPost.setHeading(jobPostRequest.getHeading());
            jobPost.setDescription(jobPostRequest.getDescription());
            jobPost.setSubjects(jobPostRequest.getSubjects());
            jobPost.setLastEditedOn(LocalDateTime.now());
            jobPost.setMinYearsExperienceRequired(jobPostRequest.getMinYearsExperienceRequired());
            jobPost.setMaxYearsExperienceRequired(jobPostRequest.getMaxYearsExperienceRequired());
            switch (jobPostRequest.getRoleType()) {
                case "FULLTIME":
                    jobPost.setEmploymentType(EmploymentType.FULLTIME);
                    break;
                case "PARTIME":
                    jobPost.setEmploymentType(EmploymentType.PARTIME);
                    break;
                case "CONTRACT":
                    jobPost.setEmploymentType(EmploymentType.CONTRACT);
                    break;
                case "PERMANENT":
                    jobPost.setEmploymentType(EmploymentType.PERMANENT);
                    break;
                default:
                    throw new IncorrectJobPostingValues("Unidentified Role Type");
            }
            return jobPostingRepository.save(jobPost);
        }
    }

    @Override
    public void markJobPostClosed(Long id) {
        JobPost jobPost = jobPostingRepository.findOneById(id);
        jobPost.setOpen(false);
        jobPostingRepository.save(jobPost);
    }

    @Override
    public Page<JobApplicationResponse> getJobApplicants(Long id, PageRequest pageRequest) throws IncorrectJobPostingValues {
        JobPost jobPost = jobPostingRepository.findOneById(id);
        if (jobPost == null)
            throw new IncorrectJobPostingValues("Job Post Not Found");
        else return appliedJobRepository.findAllByAppliedPost(jobPost, pageRequest);
    }
}
