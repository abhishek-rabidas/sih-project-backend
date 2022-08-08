package org.aicte.sih.SIHProject.api.jobs.dto.Entity;

import lombok.Getter;
import lombok.Setter;
import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.api.jobs.dto.EmploymentType;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class JobPosting extends AbstractPersistable<Long> {
    private String heading;
    private String description;
    private String subjects;
    private String collegeName;
    private String city;
    private String state;
    @OneToOne
    private CollegeEntity college;
    private LocalDateTime postedOn;
    private LocalDateTime lastEditedOn;
    private int minYearsExperienceRequired;
    private int maxYearsExperienceRequired;
    private int totalApplicants;
    private int totalViews;
    private EmploymentType roleType;
    private boolean isOpen;
}
