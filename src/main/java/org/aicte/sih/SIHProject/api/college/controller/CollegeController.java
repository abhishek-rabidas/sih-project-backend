package org.aicte.sih.SIHProject.api.college.controller;

import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.api.college.dto.request.CollegeRegistrationRequest;
import org.aicte.sih.SIHProject.commons.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/college")
public class CollegeController {

    @PostMapping
    public ResponseEntity<APIResponse<CollegeEntity>> register(@RequestBody CollegeRegistrationRequest request) {
        return null;
    }
}
