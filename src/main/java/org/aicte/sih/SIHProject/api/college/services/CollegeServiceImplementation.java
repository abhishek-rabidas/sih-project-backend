package org.aicte.sih.SIHProject.api.college.services;

import org.aicte.sih.SIHProject.api.college.Exception.CollegeExceptions;
import org.aicte.sih.SIHProject.api.college.Repository.CollegeRepository;
import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.api.college.dto.request.CollegeRegistrationRequest;
import org.aicte.sih.SIHProject.api.jobs.dao.JobPostingRepository;
import org.aicte.sih.SIHProject.api.jobs.dto.Entity.JobPost;
import org.aicte.sih.SIHProject.emailing.EmailServices;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CollegeServiceImplementation implements CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Autowired
    private EmailServices emailServices;

    @Override
    public List<CollegeEntity> getRegisteredColleges() {
        return collegeRepository.findAll();
    }

    @Override
    public CollegeEntity registerCollege(CollegeRegistrationRequest collegeDetails) {
        if (collegeRepository.countByAicteAffiliationNumber(collegeDetails.getAicteAffiliationNumber()) > 0 ||
                collegeRepository.countByUniversityRegistrationNumber(collegeDetails.getUniversityRegistrationNumber()) > 0 ||
                collegeRepository.countByEmail(collegeDetails.getEmail()) > 0 || collegeRepository.countByPhone(collegeDetails.getPhone()) > 0) {
            throw new CollegeExceptions("College is already registered");
        }

        CollegeEntity college = new CollegeEntity();
        college.setName(collegeDetails.getName());
        college.setUin(collegeDetails.getName().replaceAll(" ", "").toLowerCase(Locale.ROOT) + "_" + RandomStringUtils.randomAlphanumeric(8));
        college.setAicteAffiliationNumber(collegeDetails.getAicteAffiliationNumber());
        college.setUniversityRegistrationNumber(collegeDetails.getUniversityRegistrationNumber());
        college.setCity(collegeDetails.getCity());
        college.setState(collegeDetails.getState());
        college.setDateOfEstablishment(collegeDetails.getDateOfEstablishment());
        college.setPhone(collegeDetails.getPhone());
        college.setEmail(collegeDetails.getEmail());
        college.setCoverImageBaseUrl(collegeDetails.getCoverImageBaseUrl());
        college.setActive(true);
        emailServices.sendCollegeRegistrationSuccessfulEmail(college);
        return collegeRepository.save(college);
    }

    @Override
    public CollegeEntity fetchCollege(Long id) {
        return collegeRepository.findOneById(id);
    }

    @Override
    public void markCollegeClosed(Long id) {
        CollegeEntity collegeEntity = collegeRepository.findOneById(id);
        collegeEntity.setActive(false);
        collegeRepository.save(collegeEntity);
    }

    @Override
    public Page<JobPost> fetchAllPostedJobs(String collegeUIN, PageRequest pageRequest) {
        CollegeEntity college = collegeRepository.findOneByUin(collegeUIN);
        if (college == null)
            throw new CollegeExceptions("College Not Found");
        else return jobPostingRepository.findAllByCollegeAndIsOpenTrue(college, pageRequest);
    }

    @Override
    public Page<JobPost> fetchAllClosedJobs(String collegeUIN, PageRequest pageRequest) {
        CollegeEntity college = collegeRepository.findOneByUin(collegeUIN);
        if (college == null)
            throw new CollegeExceptions("College Not Found");
        else return jobPostingRepository.findAllByCollegeAndIsOpenFalse(college, pageRequest);
    }
}
