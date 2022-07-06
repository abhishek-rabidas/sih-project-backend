package org.aicte.sih.SIHProject.api.college.services;

import org.aicte.sih.SIHProject.api.college.Exception.CollegeExceptions;
import org.aicte.sih.SIHProject.api.college.Repository.CollegeRepository;
import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CollegeServiceImplementation implements CollegeService{


    @Autowired
    public static CollegeRepository collegeRepository;

    @Override
    public List<CollegeEntity> getRegisteredColleges() {
        return collegeRepository.findAll();
    }

    @Override
    public CollegeEntity registerCollege(Map<String,String> collegeDetails) {
        Optional<CollegeEntity> collegeEntityOptional = collegeRepository.findCollegeByUIN(collegeDetails.get("uin"));

        if(collegeEntityOptional.isPresent())
        {
            throw  new CollegeExceptions("already registered");
        }

        else if(collegeDetails.get("uin")==null || collegeDetails.get("name")==null)
        {
            throw  new CollegeExceptions("bad body found");

        }
        else
        {

            CollegeEntity college = new CollegeEntity();
            college.setName(collegeDetails.get("name"));
            college.setUin(collegeDetails.get("uin"));
            return collegeRepository.save(college);

        }


    }
}
