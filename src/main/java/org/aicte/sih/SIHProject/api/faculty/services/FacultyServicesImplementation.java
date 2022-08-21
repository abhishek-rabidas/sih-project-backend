package org.aicte.sih.SIHProject.api.faculty.services;

import org.aicte.sih.SIHProject.api.certificate.exceptions.FacultyCertificateException;
import org.aicte.sih.SIHProject.api.faculty.Exception.FacultyException;
import org.aicte.sih.SIHProject.api.faculty.dao.FacultyRepository;
import org.aicte.sih.SIHProject.api.faculty.dto.entities.Faculty;
import org.aicte.sih.SIHProject.api.faculty.dto.request.FacultyRegistrationRequest;
import org.aicte.sih.SIHProject.api.faculty.dto.response.FacultyDataResponse;
import org.aicte.sih.SIHProject.api.jobs.dao.AppliedJobRepository;
import org.aicte.sih.SIHProject.api.jobs.dto.Response.FacultyAppliedJobResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyServicesImplementation implements FacultyServices{

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private AppliedJobRepository appliedJobRepository;

    @Override
    public FacultyDataResponse registerFaculty(FacultyRegistrationRequest facultyRegistrationRequest) {
        if(facultyRepository.countByEmailAddress(facultyRegistrationRequest.getEmailAddress())>0){
            throw new FacultyException("Faculty Exists");
        }
        Faculty faculty = new Faculty();
        faculty.setFirstName(facultyRegistrationRequest.getFirstName());
        faculty.setLastName(facultyRegistrationRequest.getLastName());
        faculty.setNickName(facultyRegistrationRequest.getNickName());
        faculty.setStreet(facultyRegistrationRequest.getStreet());
        faculty.setCity(facultyRegistrationRequest.getCity());
        faculty.setPinCode(facultyRegistrationRequest.getPinCode());
        faculty.setPhoneNumber(facultyRegistrationRequest.getPhoneNumber());
        faculty.setEmailAddress(facultyRegistrationRequest.getEmailAddress());
        faculty.setDescription(facultyRegistrationRequest.getDescription());
        faculty.setJoinedOn(new Date());
        return new FacultyDataResponse(facultyRepository.save(faculty));
    }

    @Override
    public FacultyDataResponse getFaculty(Long id) {
        return new FacultyDataResponse(facultyRepository.findOneById(id));
    }

    @Override
    public List<FacultyAppliedJobResponse> getAppliedJobsByFaculty(Long id) {
        return appliedJobRepository.findAllByFaculty(facultyRepository.findOneById(id)).stream().map(FacultyAppliedJobResponse::new).collect(Collectors.toList());
    }
}
