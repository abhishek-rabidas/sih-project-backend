package org.aicte.sih.SIHProject.api.course.dto.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class CourseEntity extends AbstractPersistable<Long> {
}
