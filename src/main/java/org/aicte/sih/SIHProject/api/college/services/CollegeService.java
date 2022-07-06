package org.aicte.sih.SIHProject.api.college.services;

import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.commons.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CollegeService {

    public  List<CollegeEntity> getRegisteredColleges();
}
