package org.aicte.sih.SIHProject.api.experience.services;

import org.aicte.sih.SIHProject.api.certificate.dto.Entity.FacultyCertificate;
import org.aicte.sih.SIHProject.api.certificate.exceptions.FacultyCertificateException;
import org.aicte.sih.SIHProject.api.college.Exception.CollegeExceptions;
import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.api.experience.dao.FacultyExperienceRepository;
import org.aicte.sih.SIHProject.api.experience.dto.entity.FacultyExperience;
import org.aicte.sih.SIHProject.api.experience.dto.request.FacultyExperienceAddRequest;
import org.aicte.sih.SIHProject.api.faculty.dao.FacultyRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

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
    public FacultyExperience editExperience(Long id, FacultyExperience request) {
        FacultyExperience experience = facultyExperienceRepository.findOneById(id);
        if (experience == null) {
            throw new FacultyCertificateException("Certificate Not Found");
        } else {

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
    }

    @Override
    public void deleteExperience(Long experienceId) {
        facultyExperienceRepository.deleteById(experienceId);

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
