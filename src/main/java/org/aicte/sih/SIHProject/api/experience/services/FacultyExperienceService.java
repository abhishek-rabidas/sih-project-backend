package org.aicte.sih.SIHProject.api.experience.services;

import org.aicte.sih.SIHProject.api.experience.dto.entity.FacultyExperience;
import org.aicte.sih.SIHProject.api.experience.dto.request.FacultyExperienceAddRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FacultyExperienceService {
    FacultyExperience addNewExperience(FacultyExperienceAddRequest request);
    FacultyExperience editExperience(Long id,FacultyExperience request);
    void deleteExperience(Long experienceId);
    List<FacultyExperience> listAllExperience(Long facultyId);
    FacultyExperience viewExperience(Long facultyId, Long experienceId);
}
