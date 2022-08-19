package org.aicte.sih.SIHProject.api.faculty.dto.entities;

import lombok.Getter;
import lombok.Setter;
import org.aicte.sih.SIHProject.api.certificate.dto.Entity.FacultyCertificate;
import org.aicte.sih.SIHProject.api.experience.dto.entity.FacultyExperience;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Faculty extends AbstractPersistable<Long> {
    private String firstName;
    private String lastName;
    private String nickName;
    private String street;
    private String city;
    private String state;
    private int pinCode;
    private BigInteger phoneNumber;
    private String emailAddress;
    private String description;
    private Date joinedOn;
    @OneToMany
    private List<FacultyExperience> experiences;
    @OneToMany
    private List<FacultyCertificate> certifications;
}