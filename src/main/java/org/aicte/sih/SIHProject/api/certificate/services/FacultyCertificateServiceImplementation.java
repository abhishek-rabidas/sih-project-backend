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
        return facultyCertificateRepository.findAllByFaculty(facultyRepository.findOneById(id));
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
    public void markCertificateClosed(Long facultyId, Long certificateId) {
        FacultyCertificate facultyCertificate = facultyCertificateRepository.findOneById(certificateId);
        if(facultyId==facultyCertificate.getFaculty().getId()){
            facultyCertificate.setActive(false);
            facultyCertificateRepository.save(facultyCertificate);
        }else{
            throw new FacultyCertificateException("Not Authorized");
        }
    }

    @Override
    public FacultyCertificate updateCertificate(Long facultyId, Long certificateId, FacultyCertificate facultyCertificate) {
        FacultyCertificate certificate = facultyCertificateRepository.findOneById(certificateId);
        if (certificate == null) {
            throw new FacultyCertificateException("Certificate Not Found");
        } else if(facultyId==facultyCertificate.getFaculty().getId()) {
            certificate.setCertificateNumber(facultyCertificate.getCertificateNumber());
            certificate.setActive(true);
            certificate.setIssuerName(facultyCertificate.getIssuerName());
            certificate.setValidTill(facultyCertificate.getValidTill());
            certificate.setDateOfIssue(facultyCertificate.getDateOfIssue());
            return facultyCertificateRepository.save(facultyCertificate);
        }
        throw  new FacultyCertificateException("Not Authirized");
    }
}
