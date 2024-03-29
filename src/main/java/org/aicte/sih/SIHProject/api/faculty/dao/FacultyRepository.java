package org.aicte.sih.SIHProject.api.faculty.dao;

import org.aicte.sih.SIHProject.api.certificate.dto.Entity.FacultyCertificate;
import org.aicte.sih.SIHProject.api.faculty.dto.entities.Faculty;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findOneById(Long id);

    Long countByEmailAddress(String email);

}
