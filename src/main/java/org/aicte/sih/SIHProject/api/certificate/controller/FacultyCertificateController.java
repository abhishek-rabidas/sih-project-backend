package org.aicte.sih.SIHProject.api.certificate.controller;

import org.aicte.sih.SIHProject.api.certificate.dto.Entity.FacultyCertificate;
import org.aicte.sih.SIHProject.api.certificate.services.FacultyCertificateService;
import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.api.college.dto.request.CollegeRegistrationRequest;
import org.aicte.sih.SIHProject.api.faculty.dto.entities.Faculty;
import org.aicte.sih.SIHProject.api.jobs.dto.request.JobPostRequest;
import org.aicte.sih.SIHProject.commons.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/certificate")
public class FacultyCertificateController {


    @Autowired
    private FacultyCertificateService facultyCertificateService;

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<List<FacultyCertificate>>> getCertificateForFaculty(@PathVariable("id") Long id) {
        APIResponse<List<FacultyCertificate>> response = new APIResponse<>();
        try {
            response.setData(facultyCertificateService.getCertificateForFaculty(id));
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            response.setStatusCode(e.getStatusCode().value());
            response.setMessage(e.getStatusText());
            return ResponseEntity.internalServerError().body(response);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("internal server error");
            return ResponseEntity.internalServerError().body(response);
        }
    }


    @PostMapping
    public ResponseEntity<APIResponse<FacultyCertificate>> registerCertificate(@RequestBody FacultyCertificate facultyCertificate) {
        APIResponse<FacultyCertificate> response = new APIResponse<>();
        try {
            response.setData(facultyCertificateService.setCertificateDetails(facultyCertificate));
            return ResponseEntity.status(response.getStatusCode()).body(response);
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


    @PostMapping("/closeCertificate/{id}")
    public ResponseEntity<APIResponse> markCertificateClosed(@PathVariable("id") Long id) {
        APIResponse response = new APIResponse<>();
        try {
            facultyCertificateService.markCertificateClosed(id);
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

    @PutMapping("/updateCertificate/{id}")
    public ResponseEntity<APIResponse> updateCertificate(@PathVariable("id") Long id,@RequestBody FacultyCertificate facultyCertificate) {
        APIResponse response = new APIResponse<>();
        try {
            facultyCertificateService.updateCertificate(id,facultyCertificate);
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







