package org.aicte.sih.SIHProject.api.certificate.controller;

import org.aicte.sih.SIHProject.api.certificate.dto.Entity.FacultyCertificate;
import org.aicte.sih.SIHProject.api.certificate.dto.Request.FacultyCertificateAddRequest;
import org.aicte.sih.SIHProject.api.certificate.services.FacultyCertificateService;
import org.aicte.sih.SIHProject.commons.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/certificate")
@CrossOrigin
public class FacultyCertificateController {


    @Autowired
    private FacultyCertificateService facultyCertificateService;

    @GetMapping("/fetchAll/{id}")
    public ResponseEntity<APIResponse<List<FacultyCertificate>>> getCertificatesForFaculty(@PathVariable("id") Long id) {
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

    //TODO Pagination
    @PostMapping
    public ResponseEntity<APIResponse<FacultyCertificate>> registerCertificate(@RequestBody FacultyCertificateAddRequest facultyCertificateAddRequest) {
        APIResponse<FacultyCertificate> response = new APIResponse<>();
        try {
            response.setData(facultyCertificateService.setCertificateDetails(facultyCertificateAddRequest));
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


    @PostMapping("/closeCertificate/{facultyId}/{certificateId}")
    public ResponseEntity<APIResponse> markCertificateClosed(@PathVariable("facultyId") Long facultyId,@PathVariable("certificateId") Long certificateId) {
        APIResponse response = new APIResponse<>();
        try {
            facultyCertificateService.markCertificateClosed(facultyId,certificateId);
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

    @PutMapping("/updateCertificate/{facultyId}/{certificateId}")
    public ResponseEntity<APIResponse<FacultyCertificate>> updateCertificate(@PathVariable("facultyId") Long facultyId,
                                                                             @PathVariable("certificateId") Long certificateId,
                                                                             @RequestBody FacultyCertificate facultyCertificate) {
        APIResponse<FacultyCertificate> response = new APIResponse<>();
        try {
            response.setData(facultyCertificateService.updateCertificate(facultyId,certificateId, facultyCertificate));
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







