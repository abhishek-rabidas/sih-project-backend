package org.aicte.sih.SIHProject.api.college.dto.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class CollegeEntity extends AbstractPersistable<Long> {
    private String name;
    private String uin;
}
