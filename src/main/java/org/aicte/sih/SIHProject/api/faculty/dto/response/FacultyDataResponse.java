package org.aicte.sih.SIHProject.api.faculty.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.aicte.sih.SIHProject.api.certificate.dto.Entity.FacultyCertificate;
import org.aicte.sih.SIHProject.api.experience.dto.entity.FacultyExperience;
import org.aicte.sih.SIHProject.api.faculty.dto.entities.Faculty;

import java.util.List;

@Getter
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
    }
}
