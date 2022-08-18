package org.aicte.sih.SIHProject.api.certificate.dao;

import org.aicte.sih.SIHProject.api.certificate.dto.Entity.FacultyCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyCertificateRepository extends JpaRepository<FacultyCertificate, Long> {
    FacultyCertificate findOneById(Long id);
}
