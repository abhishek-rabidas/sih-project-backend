package org.aicte.sih.SIHProject.api.college.dto.entities;

import lombok.Getter;
import lombok.Setter;
import org.aicte.sih.SIHProject.api.course.dto.Entity.CourseEntity;
import org.springframework.data.jpa.domain.AbstractPersistable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class CollegeEntity extends AbstractPersistable<Long> {
    private String name;
    private String uin;
    private String aicteAffiliationNumber;
    private String universityRegistrationNumber;
    private String city;
    private String state;
    private String dateOfEstablishment;
    private String email;
    private String phone;
    private String coverImageBaseUrl;
    private boolean isActive;

    @OneToMany
    private List<CourseEntity> courses;
}
