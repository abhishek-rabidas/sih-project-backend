package org.aicte.sih.SIHProject.api.college.Exception;

import lombok.Setter;
@Setter
public class CollegeExceptions extends RuntimeException {
    String message;
    public CollegeExceptions(String message) {
        super(message);
    }
}
