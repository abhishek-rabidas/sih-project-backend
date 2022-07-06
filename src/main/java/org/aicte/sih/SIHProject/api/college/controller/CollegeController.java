package org.aicte.sih.SIHProject.api.college.controller;

import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.api.college.dto.request.CollegeRegistrationRequest;
import org.aicte.sih.SIHProject.api.college.services.CollegeService;
import org.aicte.sih.SIHProject.api.college.services.CollegeServiceImplementation;
import org.aicte.sih.SIHProject.commons.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/college")
public class CollegeController {


    @Autowired
    private CollegeService collegeService;

    @PostMapping
    public ResponseEntity<APIResponse<CollegeEntity>> register(@RequestBody CollegeRegistrationRequest request) {
        return null;
    }


    @GetMapping("/getRegisteredColleges")
    public ResponseEntity<APIResponse<List<CollegeEntity>>> getCollegeList()
    {
        APIResponse<List<CollegeEntity>> response = new APIResponse<>();

        try {
            response.setData(collegeService.getRegisteredColleges());
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

        //TODO Pagination
        //return collegeService.getRegisteredColleges();
    }
}
