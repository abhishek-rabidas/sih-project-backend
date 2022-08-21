package org.aicte.sih.SIHProject.api.experience.dao;

import org.aicte.sih.SIHProject.api.experience.dto.entity.FacultyExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyExperienceRepository extends JpaRepository<FacultyExperience, Long> {
    List<FacultyExperience> findAllByFaculty(FacultyExperience faculty);
    FacultyExperience findOneById(Long id);
}
