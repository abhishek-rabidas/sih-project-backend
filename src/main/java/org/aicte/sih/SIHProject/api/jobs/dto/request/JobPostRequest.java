package org.aicte.sih.SIHProject.api.jobs.dto.request;

import lombok.Getter;

@Getter
public class JobPostRequest {
    private String collegeUin;
    private String heading;
    private String description;
    private String subjects;
    private int minYearsExperienceRequired;
    private int maxYearsExperienceRequired;
    private String roleType;
}
