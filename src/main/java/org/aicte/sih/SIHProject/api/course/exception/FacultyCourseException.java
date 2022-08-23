package org.aicte.sih.SIHProject.api.course.exception;

public class FacultyCourseException extends RuntimeException{
    private String message;

    public FacultyCourseException(String message){
        super(message);
    }
}
