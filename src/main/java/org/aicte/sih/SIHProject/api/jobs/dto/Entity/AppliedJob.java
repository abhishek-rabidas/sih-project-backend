package org.aicte.sih.SIHProject.api.jobs.dto.Entity;

import lombok.Getter;
import lombok.Setter;
import org.aicte.sih.SIHProject.api.faculty.dto.entities.Faculty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class AppliedJob extends AbstractPersistable<Long> {
    @OneToOne
    private JobPosting appliedPost;

    @OneToOne
    private Faculty faculty;

    private LocalDateTime appliedOn;

    @Column(length = 1000)
    private String coverLetter;
}
