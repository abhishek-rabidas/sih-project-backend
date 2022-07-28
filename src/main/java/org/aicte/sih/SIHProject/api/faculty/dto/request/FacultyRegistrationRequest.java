package org.aicte.sih.SIHProject.api.faculty.dto.request;

import lombok.Getter;

import java.math.BigInteger;

@Getter
public class FacultyRegistrationRequest {
    private String firstName;
    private String lastName;
    private String nickName;
    private String street;
    private String city;
    private String state;
    private int pinCode;
    private BigInteger phoneNumber;
    private String emailAddress;
    private String description;
}
