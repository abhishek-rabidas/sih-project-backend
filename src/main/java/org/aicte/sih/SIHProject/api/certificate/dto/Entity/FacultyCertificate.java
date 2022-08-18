package org.aicte.sih.SIHProject.api.certificate.dto.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class FacultyCertificate extends AbstractPersistable<Long> {

}
