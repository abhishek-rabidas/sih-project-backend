package org.aicte.sih.SIHProject.api.experience.services;

import org.aicte.sih.SIHProject.api.experience.dto.entity.FacultyExperience;
import org.aicte.sih.SIHProject.api.experience.dto.request.FacultyExperienceAddRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class FacultyExperienceServiceImplementation implements FacultyExperienceService {

    @Override
    public FacultyExperience addNewExperience(FacultyExperienceAddRequest request) {
        return null;
    }

    @Override
    public FacultyExperience editExperience(FacultyExperienceAddRequest request) {
        return null;
    }

    @Override
    public void deleteExperience(Long facultyId, Long experienceId) {

    }

    @Override
    public Page<FacultyExperience> listAllExperience(Long facultyId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public FacultyExperience viewExperience(Long facultyId, Long experienceId) {
        return null;
    }
}
