package org.aicte.sih.SIHProject.api.college.dto.entities;

import lombok.Builder;

import javax.persistence.Entity;

@Entity
@Builder
public class CollegeEntity {
    private String name;
}
