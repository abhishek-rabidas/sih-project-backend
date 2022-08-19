package org.aicte.sih.SIHProject.api.experience.services;

import org.aicte.sih.SIHProject.api.experience.dto.entity.FacultyExperience;
import org.aicte.sih.SIHProject.api.experience.dto.request.FacultyExperienceAddRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface FacultyExperienceService {
    FacultyExperience addNewExperience(FacultyExperienceAddRequest request);
    FacultyExperience editExperience(FacultyExperienceAddRequest request);
    void deleteExperience(Long facultyId, Long experienceId);
    Page<FacultyExperience> listAllExperience(Long facultyId, PageRequest pageRequest);
    FacultyExperience viewExperience(Long facultyId, Long experienceId);
}
