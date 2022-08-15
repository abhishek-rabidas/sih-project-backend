package org.aicte.sih.SIHProject.api.college.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollegeRegistrationRequest {
    private String name;
    private String aicteAffiliationNumber;
    private String universityRegistrationNumber;
    private String city;
    private String state;
    private String dateOfEstablishment;
    private String email;
    private String phone;
    private String coverImageBaseUrl;
}
