package org.aicte.sih.SIHProject.api.college.services;

import org.aicte.sih.SIHProject.api.college.Repository.CollegeRepository;
import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public class CollegeServiceImplementation implements CollegeService{


    @Autowired
    public static CollegeRepository collegeRepository;

    @Override
    public List<CollegeEntity> getRegisteredColleges() {
        return collegeRepository.findAll();
    }
}
