package org.aicte.sih.SIHProject.api.jobs.dto.Response;

import lombok.Getter;
import lombok.Setter;
import org.aicte.sih.SIHProject.api.faculty.dto.entities.Faculty;
import org.aicte.sih.SIHProject.api.jobs.dto.Entity.AppliedJob;

import java.time.LocalDateTime;

@Getter
@Setter
public class JobApplicationResponse {
    private String coverLetter;
    private LocalDateTime appliedOn;
    private Faculty faculty;

    public JobApplicationResponse(AppliedJob appliedJob) {
        this.coverLetter = appliedJob.getCoverLetter();
        this.appliedOn = appliedJob.getAppliedOn();
        this.faculty = appliedJob.getFaculty();
    }
}
