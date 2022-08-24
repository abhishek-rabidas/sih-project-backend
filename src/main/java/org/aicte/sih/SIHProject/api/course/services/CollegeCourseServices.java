package org.aicte.sih.SIHProject.api.course.services;

import org.aicte.sih.SIHProject.api.course.dto.Entity.CourseEntity;
import org.aicte.sih.SIHProject.api.course.dto.Request.CourseAddRequest;

import java.util.List;

public interface CollegeCourseServices {
    List<CourseEntity> getAllRegisteredCourses(Long collegeId);

    CourseEntity registerCourse(Long collegeId, CourseAddRequest courseAddRequest);

    void markSubjectClosed(Long collegeId, Long courseId);

    CourseEntity updateCourseDetails(Long collegeId, Long courseId, CourseEntity courseEntity);
}
