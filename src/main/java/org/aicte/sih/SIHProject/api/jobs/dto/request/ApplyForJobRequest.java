package org.aicte.sih.SIHProject.api.jobs.dto.request;

import lombok.Getter;

@Getter
public class ApplyForJobRequest {
    private String coverLetter;
    private Long jobPosId;
    private Long facultyId;
}
