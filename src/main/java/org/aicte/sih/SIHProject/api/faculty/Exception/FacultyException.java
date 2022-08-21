package org.aicte.sih.SIHProject.api.faculty.Exception;

public class FacultyException extends  RuntimeException{

    String message;

    public FacultyException(String message){
        super(message);
    }
}
