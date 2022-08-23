package org.aicte.sih.SIHProject.api.experience.services;

import org.aicte.sih.SIHProject.api.certificate.exceptions.FacultyCertificateException;
import org.aicte.sih.SIHProject.api.experience.dao.FacultyExperienceRepository;
import org.aicte.sih.SIHProject.api.experience.dto.entity.FacultyExperience;
import org.aicte.sih.SIHProject.api.experience.dto.request.FacultyExperienceAddRequest;
import org.aicte.sih.SIHProject.api.faculty.Exception.FacultyException;
import org.aicte.sih.SIHProject.api.faculty.dao.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyExperienceServiceImplementation implements FacultyExperienceService {


    @Autowired
    private FacultyExperienceRepository facultyExperienceRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public FacultyExperience addNewExperience(FacultyExperienceAddRequest request) {

        FacultyExperience facultyExperience = new FacultyExperience();
        facultyExperience.setOverview(request.getOverview());
        facultyExperience.setLocation(request.getLocation());
        facultyExperience.setSkills(request.getSkills());
        facultyExperience.setOrganization(request.getOrganization());
        facultyExperience.setEndMonth(request.getEndMonth());
        facultyExperience.setStartYear(request.getStartYear());
        facultyExperience.setEndYear(request.getEndYear());
        facultyExperience.setStartMonth(request.getStartMonth());
        facultyExperience.setFaculty(facultyRepository.findOneById(request.getFacultyId()));
        return facultyExperienceRepository.save(facultyExperience);
    }

    @Override
    public FacultyExperience editExperience(Long experienceId, Long facultyId, FacultyExperience request) {
        FacultyExperience experience = facultyExperienceRepository.findOneById(experienceId);
        if (experience == null) {
            throw new FacultyCertificateException("Experience Not Found");
        } else if (experience.getFaculty().getId() == facultyId) {

            experience.setSkills(request.getSkills());
            experience.setOrganization(request.getOrganization());
            experience.setLocation(request.getLocation());
            experience.setEndYear(request.getEndYear());
            experience.setStartYear(request.getStartYear());
            experience.setStartMonth(request.getStartMonth());
            experience.setEndMonth(request.getEndMonth());
            experience.setOverview(request.getOverview());
            return facultyExperienceRepository.save(experience);
        }
        throw new FacultyException("Not Authorized");
    }

    @Override
    public void deleteExperience(Long facultyId, Long experienceId) {
        FacultyExperience facultyExperience = facultyExperienceRepository.findOneById(experienceId);
        if (facultyId == facultyExperience.getFaculty().getId()) {
            facultyExperienceRepository.deleteById(experienceId);
        } else {
            throw new FacultyException("Not Authorized");
        }
    }

    @Override
    public List<FacultyExperience> listAllExperienceByFaculty(Long id) {
        return facultyExperienceRepository.findAllByFaculty(facultyExperienceRepository.findOneById(id));
    }

    @Override
    public FacultyExperience viewExperience(Long facultyId, Long experienceId) {
        return null;
    }
}
