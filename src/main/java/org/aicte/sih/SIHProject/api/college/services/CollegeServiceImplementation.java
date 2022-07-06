package org.aicte.sih.SIHProject.api.college.services;

import org.aicte.sih.SIHProject.api.college.Exception.CollegeExceptions;
import org.aicte.sih.SIHProject.api.college.Repository.CollegeRepository;
import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.api.college.dto.request.CollegeRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        } else if (collegeDetails.getUin() == null || collegeDetails.getName() == null) {
            throw new CollegeExceptions("bad body found");

        } else {

            CollegeEntity college = new CollegeEntity();
            college.setName(collegeDetails.getName());
            college.setUin(collegeDetails.getUin());
            return collegeRepository.save(college);

        }
    }
}
