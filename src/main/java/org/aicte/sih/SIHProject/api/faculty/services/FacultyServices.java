package org.aicte.sih.SIHProject.api.faculty.services;

import org.aicte.sih.SIHProject.api.faculty.dto.request.FacultyRegistrationRequest;
import org.aicte.sih.SIHProject.api.faculty.dto.response.FacultyDataResponse;

public interface FacultyServices {
    FacultyDataResponse registerFaculty(FacultyRegistrationRequest facultyRegistrationRequest);
    FacultyDataResponse getFaculty(Long id);
}
