package org.aicte.sih.SIHProject.api.course.dto.Entity;

import lombok.Getter;
import lombok.Setter;
import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.api.faculty.dto.entities.Faculty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class CourseEntity extends AbstractPersistable<Long> {


    private String Subject;
    private int totalNoOfModules;
    private int coveredModules;
    private String subjectCode;
    private String startDate;
    private String presentDate;
    private String expectedEndDate;
    private boolean isActive= true;
    @OneToOne
    private CollegeEntity college;


}
