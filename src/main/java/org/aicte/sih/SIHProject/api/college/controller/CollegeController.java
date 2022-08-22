package org.aicte.sih.SIHProject.api.college.controller;

import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.api.college.dto.request.CollegeRegistrationRequest;
import org.aicte.sih.SIHProject.api.college.services.CollegeService;
import org.aicte.sih.SIHProject.api.faculty.dto.response.FacultyDataResponse;
import org.aicte.sih.SIHProject.api.jobs.dto.Entity.JobPost;
import org.aicte.sih.SIHProject.commons.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/college")
@CrossOrigin
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @GetMapping()
    public ResponseEntity<APIResponse<List<CollegeEntity>>> getCollegeList() {
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

    @PostMapping
    public ResponseEntity<APIResponse<CollegeEntity>> registerCollege(@RequestBody CollegeRegistrationRequest collegeDetails) {
        APIResponse<CollegeEntity> response = new APIResponse<>();
        try {
            response.setData(collegeService.registerCollege(collegeDetails));
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

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<CollegeEntity>> getFaculty(@PathVariable("id") Long id) {
        APIResponse<CollegeEntity> response = new APIResponse<>();
        try {
            response.setData(collegeService.fetchCollege(id));
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

    @PostMapping("/closeCollege/{id}")
    public ResponseEntity<APIResponse> markJobPostClosed(@PathVariable("id") Long id) {
        APIResponse response = new APIResponse<>();
        try {
            collegeService.markCollegeClosed(id);
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

    @GetMapping("/postedJobs/{uin}")
    public ResponseEntity<APIResponse<Page<JobPost>>> fetchJobPostedByCollege(@RequestParam("pageNumber") int pageNumber,
                                                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                                              @RequestParam("sortBy") String sortBy, @PathVariable("uin") String collegeUIN) {
        APIResponse<Page<JobPost>> response = new APIResponse<>();
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        try {
            response.setData(collegeService.fetchAllPostedJobs(collegeUIN, pageRequest));
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

    @GetMapping("/closedJobs/{uin}")
    public ResponseEntity<APIResponse<Page<JobPost>>> getAllClosedJobs(@RequestParam("pageNumber") int pageNumber,
                                                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                                       @RequestParam("sortBy") String sortBy, @PathVariable("uin") String collegeUIN) {
        APIResponse<Page<JobPost>> response = new APIResponse<>();
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        try {
            response.setData(collegeService.fetchAllClosedJobs(collegeUIN, pageRequest));
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
