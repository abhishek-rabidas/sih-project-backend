package org.aicte.sih.SIHProject.api.certificate.dto.Request;

import lombok.Getter;

@Getter
public class FacultyCertificateAddRequest {
    private Long facultyId;
    private String certificateNumber;
    private String issuerName;
    private String dateOfIssue;
    private String validTill;
    private boolean isActive;

}
