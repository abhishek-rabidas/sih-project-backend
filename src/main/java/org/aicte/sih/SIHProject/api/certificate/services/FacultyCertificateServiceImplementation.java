package org.aicte.sih.SIHProject.api.certificate.services;

import org.aicte.sih.SIHProject.api.certificate.dao.FacultyCertificateRepository;
import org.aicte.sih.SIHProject.api.certificate.dto.Entity.FacultyCertificate;
import org.aicte.sih.SIHProject.api.certificate.dto.Request.FacultyCertificateAddRequest;
import org.aicte.sih.SIHProject.api.certificate.exceptions.FacultyCertificateException;
import org.aicte.sih.SIHProject.api.faculty.dao.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyCertificateServiceImplementation implements FacultyCertificateService{

    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private FacultyCertificateRepository facultyCertificateRepository;
    @Override
    public List<FacultyCertificate> getCertificateForFaculty(Long id) {
        return facultyCertificateRepository.findAll();
    }

    @Override
    public FacultyCertificate setCertificateDetails(FacultyCertificateAddRequest facultyCertificate) {
       if(facultyCertificateRepository.countByCertificateNumber(facultyCertificate.getCertificateNumber())>0){
           throw new FacultyCertificateException("Certificate Exists");
       }

        FacultyCertificate certificate = new FacultyCertificate();
        certificate.setCertificateNumber(facultyCertificate.getCertificateNumber());
        certificate.setIssuerName(facultyCertificate.getIssuerName());
        certificate.setValidTill(facultyCertificate.getValidTill());
        certificate.setDateOfIssue(facultyCertificate.getDateOfIssue());
        certificate.setActive(true);
        certificate.setFaculty(facultyRepository.findOneById(facultyCertificate.getFacultyId()));
        return facultyCertificateRepository.save(certificate);
    }

    @Override
    public void markCertificateClosed(Long id) {
        FacultyCertificate facultyCertificate = facultyCertificateRepository.findOneById(id);
        facultyCertificate.setActive(false);
        facultyCertificateRepository.save(facultyCertificate);
    }

    @Override
    public FacultyCertificate updateCertificate(Long id, FacultyCertificate facultyCertificate) {
        FacultyCertificate certificate = facultyCertificateRepository.findOneById(id);
        if (certificate == null) {
            throw new FacultyCertificateException("Certificate Not Found");
        } else {

            certificate.setCertificateNumber(facultyCertificate.getCertificateNumber());
            certificate.setActive(true);
            certificate.setIssuerName(facultyCertificate.getIssuerName());
            certificate.setValidTill(facultyCertificate.getValidTill());
            certificate.setDateOfIssue(facultyCertificate.getDateOfIssue());

            return facultyCertificateRepository.save(facultyCertificate);
        }
    }
}
