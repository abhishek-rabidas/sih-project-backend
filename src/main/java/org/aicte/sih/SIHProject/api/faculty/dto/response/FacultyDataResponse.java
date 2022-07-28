package org.aicte.sih.SIHProject.api.faculty.dto.response;

import lombok.Setter;
import org.aicte.sih.SIHProject.api.faculty.dto.entities.Faculty;
import org.aicte.sih.SIHProject.api.faculty.dto.entities.FacultyCertification;
import org.aicte.sih.SIHProject.api.faculty.dto.entities.FacultyExperience;

import java.math.BigInteger;
import java.util.List;

@Setter
public class FacultyDataResponse {
    private String firstName;
    private String lastName;
    private String nickName;
    private String street;
    private String city;
    private String state;
    private int pinCode;
    private String phoneNumber;
    private String emailAddress;
    private String description;
    private String joinedOn;
    private List<FacultyExperience> experiences;
    private List<FacultyCertification> certifications;

    public FacultyDataResponse(Faculty faculty) {
        this.firstName = faculty.getFirstName();
        this.lastName = faculty.getLastName();
        this.nickName = faculty.getNickName();
        this.street = faculty.getStreet();
        this.city = faculty.getCity();
        this.pinCode = faculty.getPinCode();
        this.phoneNumber = faculty.getPhoneNumber().toString();
        this.emailAddress = faculty.getEmailAddress();
        this.description = faculty.getDescription();
        this.joinedOn = faculty.getJoinedOn().toString();
        this.experiences = faculty.getExperiences();
        this.certifications = faculty.getCertifications();
    }
}
