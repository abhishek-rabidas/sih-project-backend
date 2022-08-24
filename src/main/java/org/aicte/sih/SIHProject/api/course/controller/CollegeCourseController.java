package org.aicte.sih.SIHProject.api.course.controller;


import org.aicte.sih.SIHProject.api.course.dto.Entity.CourseEntity;
import org.aicte.sih.SIHProject.api.course.dto.Request.CourseAddRequest;
import org.aicte.sih.SIHProject.api.course.services.CollegeCourseServices;
import org.aicte.sih.SIHProject.commons.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@CrossOrigin
public class CollegeCourseController {

    @Autowired
    private CollegeCourseServices collegeCourseServices;


    @GetMapping("/{collegeId}")
    public ResponseEntity<APIResponse<List<CourseEntity>>> getCollegeList(@PathVariable ("collegeId")Long collegeId) {
        APIResponse<List<CourseEntity>> response = new APIResponse<>();
        try {
            response.setData(collegeCourseServices.getAllRegisteredCourses(collegeId));
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

    @PostMapping("/{collegeId}")
    public ResponseEntity<APIResponse<CourseEntity>> registerCourses(@PathVariable ("collegeId") Long collegeId,
                                                                     @RequestBody CourseAddRequest courseAddRequest) {

        APIResponse<CourseEntity> response = new APIResponse<>();
        try {
            response.setData(collegeCourseServices.registerCourse(collegeId,courseAddRequest));
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


    @PostMapping("/closeSubject/{collegeId}/{courseId}")
    public ResponseEntity<APIResponse> markCertificateClosed(@PathVariable("collegeId") Long collegeId,
                                                             @PathVariable("courseId") Long courseId) {
        APIResponse response = new APIResponse<>();
        try {
            collegeCourseServices.markSubjectClosed(collegeId,courseId);
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
    @PutMapping("/updateCourse/{collegeId}/{courseId}")
    public ResponseEntity<APIResponse<CourseEntity>> updateCertificate(@PathVariable("collegeId") Long collegeId,
                                                                             @PathVariable("courseId") Long courseId,
                                                                             @RequestBody CourseEntity courseEntity) {
        APIResponse<CourseEntity> response = new APIResponse<>();
        try {
            response.setData(collegeCourseServices.updateCourseDetails(collegeId,courseId, courseEntity));
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
