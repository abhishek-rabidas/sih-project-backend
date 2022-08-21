package org.aicte.sih.SIHProject.api.experience.dto.request;

import lombok.Getter;

@Getter
public class FacultyExperienceAddRequest {
    private Long facultyId;
    private String overview;
    private String organization;
    private String location;
    private String startMonth;
    private int startYear;
    private String endMonth;
    private int endYear;
    private String skills;
}
