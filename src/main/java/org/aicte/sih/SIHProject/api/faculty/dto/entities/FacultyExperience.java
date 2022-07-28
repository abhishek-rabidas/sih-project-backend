package org.aicte.sih.SIHProject.api.faculty.dto.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
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
