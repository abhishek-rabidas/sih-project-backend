package org.aicte.sih.SIHProject.api.certificate.exceptions;

public class FacultyCertificateException extends RuntimeException {

    String message;
    public FacultyCertificateException(String message) {
        super(message);
    }
}
