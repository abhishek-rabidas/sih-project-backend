package org.aicte.sih.SIHProject.api.jobs.dto.Response;

import lombok.Getter;
import lombok.Setter;
import org.aicte.sih.SIHProject.api.jobs.dto.Entity.AppliedJob;
import org.aicte.sih.SIHProject.api.jobs.dto.Entity.JobPosting;

import java.time.LocalDateTime;

@Getter
@Setter
public class FacultyAppliedJobResponse {
    private JobPosting appliedPost;
    private LocalDateTime appliedOn;
    private String coverLetter;

    public FacultyAppliedJobResponse(AppliedJob appliedJob) {
        this.appliedPost = appliedJob.getAppliedPost();
        this.appliedOn = appliedJob.getAppliedOn();
        this.coverLetter = appliedJob.getCoverLetter();
    }
}
