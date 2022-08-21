package org.aicte.sih.SIHProject.api.experience.dao;

import org.aicte.sih.SIHProject.api.experience.dto.entity.FacultyExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyExperienceRepository extends JpaRepository<FacultyExperience, Long> {
}
