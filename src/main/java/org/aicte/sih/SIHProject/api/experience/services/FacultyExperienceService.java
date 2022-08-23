package org.aicte.sih.SIHProject.api.experience.services;

import org.aicte.sih.SIHProject.api.experience.dto.entity.FacultyExperience;
import org.aicte.sih.SIHProject.api.experience.dto.request.FacultyExperienceAddRequest;

import java.util.List;

public interface FacultyExperienceService {
    FacultyExperience addNewExperience(FacultyExperienceAddRequest request);
    FacultyExperience editExperience(Long id, Long facultyId, FacultyExperience request);
    void deleteExperience(Long facultyId, Long experienceId);
    List<FacultyExperience> listAllExperienceByFaculty(Long facultyId);
    FacultyExperience viewExperience(Long facultyId, Long experienceId);
}
