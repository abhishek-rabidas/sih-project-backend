package org.aicte.sih.SIHProject.api.faculty.services;

import org.aicte.sih.SIHProject.api.faculty.dto.request.FacultyRegistrationRequest;
import org.aicte.sih.SIHProject.api.faculty.dto.response.FacultyDataResponse;
import org.aicte.sih.SIHProject.api.jobs.dto.Response.FacultyAppliedJobResponse;

import java.util.List;

public interface FacultyServices {
    FacultyDataResponse registerFaculty(FacultyRegistrationRequest facultyRegistrationRequest);
    FacultyDataResponse getFaculty(Long id);
    List<FacultyAppliedJobResponse> getAppliedJobsByFaculty(Long id);
}
