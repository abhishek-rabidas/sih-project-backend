package org.aicte.sih.SIHProject.api.certificate.services;

import org.aicte.sih.SIHProject.api.certificate.dto.Entity.FacultyCertificate;
import org.aicte.sih.SIHProject.api.certificate.dto.Request.FacultyCertificateAddRequest;

import java.util.List;

public interface FacultyCertificateService {

    List<FacultyCertificate> getCertificateForFaculty(Long id);

    FacultyCertificate setCertificateDetails(FacultyCertificateAddRequest facultyCertificate);

    void markCertificateClosed(Long id);

    FacultyCertificate updateCertificate(Long id, FacultyCertificate facultyCertificate);
}
