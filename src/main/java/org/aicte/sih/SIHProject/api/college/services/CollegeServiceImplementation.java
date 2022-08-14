package org.aicte.sih.SIHProject.api.college.services;

import org.aicte.sih.SIHProject.api.college.Exception.CollegeExceptions;
import org.aicte.sih.SIHProject.api.college.Repository.CollegeRepository;
import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.api.college.dto.request.CollegeRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CollegeServiceImplementation implements CollegeService {
    @Autowired
    public CollegeRepository collegeRepository;
    @Override
    public List<CollegeEntity> getRegisteredColleges() {
        return collegeRepository.findAll();
    }
    @Override
    public CollegeEntity registerCollege(CollegeRegistrationRequest collegeDetails) {
        if (collegeRepository.countByUin(collegeDetails.getUin()) > 0) {
            throw new CollegeExceptions("already registered");
        } else if (collegeDetails.getUin() == null || collegeDetails.getName() == null || collegeDetails.getAicteAffiliationNumber() == null || collegeDetails.getDateOfEstablishment() == null || collegeDetails.getUniversityRegistrationNumber() == null || collegeDetails.getPhone() == null || collegeDetails.getEmail() == null || collegeDetails.getState() == null || collegeDetails.getCity() == null) {
            throw new CollegeExceptions("bad body found");
        } else {
            CollegeEntity college = new CollegeEntity();
            college.setName(collegeDetails.getName());
            college.setUin(collegeDetails.getUin());
            college.setCity(collegeDetails.getCity());
            college.setPhone(collegeDetails.getPhone());
            college.setDateOfEstablishment(collegeDetails.getDateOfEstablishment());
            college.setCoverImageBaseUrl(collegeDetails.getCoverImageBaseUrl());
            college.setAicteAffiliationNumber(collegeDetails.getAicteAffiliationNumber());
            college.setUniversityRegistrationNumber(collegeDetails.getUniversityRegistrationNumber());
            return collegeRepository.save(college);
        }
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
}
