package org.aicte.sih.SIHProject.api.jobs.services;

import org.aicte.sih.SIHProject.api.college.Exception.CollegeExceptions;
import org.aicte.sih.SIHProject.api.college.Repository.CollegeRepository;
import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.api.faculty.dao.FacultyRepository;
import org.aicte.sih.SIHProject.api.faculty.dto.entities.Faculty;
import org.aicte.sih.SIHProject.api.jobs.dao.AppliedJobRepository;
import org.aicte.sih.SIHProject.api.jobs.dao.JobPostingRepository;
import org.aicte.sih.SIHProject.api.jobs.dto.EmploymentType;
import org.aicte.sih.SIHProject.api.jobs.dto.Entity.AppliedJob;
import org.aicte.sih.SIHProject.api.jobs.dto.Entity.JobPost;
import org.aicte.sih.SIHProject.api.jobs.dto.Response.JobApplicationResponse;
import org.aicte.sih.SIHProject.api.jobs.dto.request.ApplyForJobRequest;
import org.aicte.sih.SIHProject.api.jobs.dto.request.JobPostRequest;
import org.aicte.sih.SIHProject.api.jobs.exceptions.IncorrectJobPostingValues;
import org.aicte.sih.SIHProject.api.jobs.exceptions.JobPostingDataFound;
import org.aicte.sih.SIHProject.emailing.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private EmailServices emailServices;

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
            try {
                emailServices.newJobPostedEmail(jobPost);
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage());
            } finally {
                return jobPostingRepository.save(jobPost);
            }
        }
    }

    @Override
    public Page<JobPost> listAllJobPostings(PageRequest pageRequest) {
        return jobPostingRepository.findAllByIsOpenTrue(pageRequest);
    }

    @Override
    public void applyForJobPost(ApplyForJobRequest request) throws IncorrectJobPostingValues {
        if (appliedJobRepository.countByAppliedPostAndFaculty(request.getJobPostId(), request.getFacultyId()) > 0)
            throw new IncorrectJobPostingValues("Already Applied For this job post!");

        AppliedJob appliedJob = new AppliedJob();
        appliedJob.setAppliedOn(LocalDateTime.now());
        JobPost jobPost = jobPostingRepository.findOneById(request.getJobPostId());
        jobPost.setTotalApplicants(jobPost.getTotalApplicants() + 1);
        appliedJob.setAppliedPost(jobPostingRepository.save(jobPost));
        appliedJob.setFaculty(facultyRepository.findOneById(request.getFacultyId()));
        appliedJob.setCoverLetter(request.getCoverLetter());
        try {
            emailServices.sendAppliedSuccessfullyEmail(appliedJob);
            emailServices.sendNewApplicantEmail(appliedJob);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        } finally {
            appliedJobRepository.save(appliedJob);
        }
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
    public List<JobApplicationResponse> getJobApplicants(Long id, PageRequest pageRequest) throws IncorrectJobPostingValues {
        JobPost jobPost = jobPostingRepository.findOneById(id);
        if (jobPost == null)
            throw new IncorrectJobPostingValues("Job Post Not Found");
        else return appliedJobRepository.findAllByAppliedPost(jobPost, pageRequest).map(JobApplicationResponse::new).stream().collect(Collectors.toList());
    }

    @Override
    public void shortlistFaculty(Long facultyId, Long jobPostId) throws JobPostingDataFound {
        JobPost jobPost = jobPostingRepository.findOneById(jobPostId);
        Faculty faculty = facultyRepository.findOneById(facultyId);
        if (jobPost.isOpen() && jobPost != null && faculty != null) {
            try {
                emailServices.sendShortlistedEmail(faculty, jobPost);
                jobPost.setOpen(false);
                jobPostingRepository.save(jobPost);
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        } else {
            throw new JobPostingDataFound("Details Does Not Found");
        }
    }
}
