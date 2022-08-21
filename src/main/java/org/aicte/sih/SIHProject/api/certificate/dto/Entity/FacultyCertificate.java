package org.aicte.sih.SIHProject.api.certificate.dto.Entity;

import lombok.Getter;
import lombok.Setter;
import org.aicte.sih.SIHProject.api.faculty.dto.entities.Faculty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class FacultyCertificate extends AbstractPersistable<Long> {

    private String certificateNumber;
    private String issuerName;
    private String dateOfIssue;
    private String validTill;
    private boolean isActive;
    @OneToOne
    private Faculty faculty;
}
