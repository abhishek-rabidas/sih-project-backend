package org.aicte.sih.SIHProject.api.certificate.dao;

import org.aicte.sih.SIHProject.api.certificate.dto.Entity.FacultyCertificate;
import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.api.faculty.dto.entities.Faculty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyCertificateRepository extends JpaRepository<FacultyCertificate, Long> {
    FacultyCertificate findOneById(Long id);
    long countByCertificateNumber(String certificateNumber);
    List<FacultyCertificate> findAllByFaculty(Faculty faculty);
}
