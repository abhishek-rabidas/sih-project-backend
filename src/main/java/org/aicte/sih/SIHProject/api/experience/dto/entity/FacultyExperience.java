package org.aicte.sih.SIHProject.api.experience.dto.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class FacultyExperience extends AbstractPersistable<Long> {
    private String overview;
    private String organization;
    private String location;
    private String startMonth;
    private int startYear;
    private String endMonth;
    private int endYear;
    private String skills;
}
