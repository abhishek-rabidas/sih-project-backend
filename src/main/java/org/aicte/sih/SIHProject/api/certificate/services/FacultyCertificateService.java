package org.aicte.sih.SIHProject.api.certificate.services;

import org.aicte.sih.SIHProject.api.certificate.dto.Entity.FacultyCertificate;

import java.util.List;

public interface FacultyCertificateService {

    List<FacultyCertificate> getCertificateForFaculty(Long id);

    FacultyCertificate setCertificateDetails(FacultyCertificate facultyCertificate);

    void markCertificateClosed(Long id);

    FacultyCertificate updateCertificate(Long id, FacultyCertificate facultyCertificate);
}
