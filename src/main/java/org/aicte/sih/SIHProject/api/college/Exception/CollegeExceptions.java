package org.aicte.sih.SIHProject.api.college.Exception;

import lombok.Setter;
import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;

@Setter
public class CollegeExceptions extends RuntimeException {

    String message;

    public CollegeExceptions(String message) {
        super(message);
    }


}
