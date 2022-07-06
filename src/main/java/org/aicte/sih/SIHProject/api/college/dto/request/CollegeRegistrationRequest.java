package org.aicte.sih.SIHProject.api.college.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollegeRegistrationRequest {
    private String name;
    private String uin;
    public CollegeRegistrationRequest(){};
}
