package org.aicte.sih.SIHProject.api.faculty.controller;

import org.aicte.sih.SIHProject.api.faculty.dto.request.FacultyRegistrationRequest;
import org.aicte.sih.SIHProject.api.faculty.dto.response.FacultyDataResponse;
import org.aicte.sih.SIHProject.api.faculty.services.FacultyServices;
import org.aicte.sih.SIHProject.api.jobs.dto.Response.FacultyAppliedJobResponse;
import org.aicte.sih.SIHProject.commons.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/faculty")
public class FacultyController {

    @Autowired
    private FacultyServices facultyServices;

    @PostMapping
    public ResponseEntity<APIResponse<FacultyDataResponse>> register(@RequestBody FacultyRegistrationRequest facultyRegistrationRequest) {
        APIResponse<FacultyDataResponse> response = new APIResponse<>();
        try {
            response.setData(facultyServices.registerFaculty(facultyRegistrationRequest));
            return ResponseEntity.ok(response);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            response.setStatusCode(e.getStatusCode().value());
            response.setMessage(e.getStatusText());
            return ResponseEntity.internalServerError().body(response);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<FacultyDataResponse>> getFaculty(@PathVariable("id") Long id) {
        APIResponse<FacultyDataResponse> response = new APIResponse<>();
        try {
            response.setData(facultyServices.getFaculty(id));
            return ResponseEntity.ok(response);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            response.setStatusCode(e.getStatusCode().value());
            response.setMessage(e.getStatusText());
            return ResponseEntity.internalServerError().body(response);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/applied/{id}")
    public ResponseEntity<APIResponse<List<FacultyAppliedJobResponse>>> getAppliedJobs(@PathVariable("id") Long id) {
        APIResponse<List<FacultyAppliedJobResponse>> response = new APIResponse<>();
        try {
            response.setData(facultyServices.getAppliedJobsByFaculty(id));
            return ResponseEntity.ok(response);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            response.setStatusCode(e.getStatusCode().value());
            response.setMessage(e.getStatusText());
            return ResponseEntity.internalServerError().body(response);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

}
