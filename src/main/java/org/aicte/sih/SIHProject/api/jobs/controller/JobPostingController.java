package org.aicte.sih.SIHProject.api.jobs.controller;

import org.aicte.sih.SIHProject.api.jobs.dto.Entity.JobPost;
import org.aicte.sih.SIHProject.api.jobs.dto.request.ApplyForJobRequest;
import org.aicte.sih.SIHProject.api.jobs.dto.request.JobPostRequest;
import org.aicte.sih.SIHProject.api.jobs.services.JobPostingServices;
import org.aicte.sih.SIHProject.commons.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/api/v1/posting")
public class JobPostingController {

    @Autowired
    private JobPostingServices postingServices;

    @PostMapping
    public ResponseEntity<APIResponse<JobPost>> postNewJob(@RequestBody JobPostRequest jobPostRequest) {
        APIResponse<JobPost> response = new APIResponse<>();
        try {
            response.setData(postingServices.addNewJobPost(jobPostRequest));
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

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<JobPost>> editJobPost(@RequestBody JobPostRequest jobPost, @PathVariable("id") Long id) {
        APIResponse<JobPost> response = new APIResponse<>();
        try {
            response.setData(postingServices.editJobPost(jobPost, id));
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

    @PostMapping("/closePost/{id}")
    public ResponseEntity<APIResponse> markJobPostClosed(@PathVariable("id") Long id) {
        APIResponse response = new APIResponse<>();
        try {
            postingServices.markJobPostClosed(id);
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


    @GetMapping
    public ResponseEntity<APIResponse> getAllJobPostings(@RequestParam("pageNumber") int pageNumber,
                                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                         @RequestParam("sortBy") String sortBy) {
        APIResponse<Page<JobPost>> response = new APIResponse<>();
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        try {
            response.setData(postingServices.listAllJobPostings(pageRequest));
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

    @PostMapping("/apply")
    public ResponseEntity<APIResponse> apply(@RequestBody ApplyForJobRequest request) {
        APIResponse response = new APIResponse<>();
        try {
            postingServices.applyForJobPost(request);
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
