package org.aicte.sih.SIHProject.api.course.exception;

public class CollegeCourseException extends RuntimeException{
    private String message;

    public CollegeCourseException(String message){
        super(message);
    }
}
