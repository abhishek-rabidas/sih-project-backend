package org.aicte.sih.SIHProject.api.course.services;


import org.aicte.sih.SIHProject.api.certificate.exceptions.FacultyCertificateException;
import org.aicte.sih.SIHProject.api.college.Exception.CollegeExceptions;
import org.aicte.sih.SIHProject.api.college.Repository.CollegeRepository;
import org.aicte.sih.SIHProject.api.course.dao.CollegeCourseRepository;
import org.aicte.sih.SIHProject.api.course.dto.Entity.CourseEntity;
import org.aicte.sih.SIHProject.api.course.dto.Request.CourseAddRequest;
import org.aicte.sih.SIHProject.api.course.exception.CollegeCourseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeCourseServiceImplementation implements CollegeCourseServices {

    @Autowired
    private CollegeCourseRepository collegeCourseRepository;
    @Autowired
    private CollegeRepository collegeRepository;

    @Override
    public List<CourseEntity> getAllRegisteredCourses(Long collegeId) {


        return collegeCourseRepository.findAllByCollege(collegeRepository.findOneById(collegeId));
    }

    @Override
    public CourseEntity registerCourse(Long collegeId, CourseAddRequest courseAddRequest) {

        if (collegeCourseRepository.countBySubjectCode(courseAddRequest.getSubjectCode()) > 0) {
            throw new FacultyCertificateException("Subject Exists");
        } else if (collegeId == collegeRepository.findOneById(collegeId).getId()) {
            CourseEntity courseEntity = new CourseEntity();
            courseEntity.setSubject(courseAddRequest.getSubject());
            courseEntity.setSubjectCode(courseAddRequest.getSubjectCode());
            courseEntity.setPresentDate(courseAddRequest.getPresentDate());
            courseEntity.setStartDate(courseAddRequest.getStartDate());
            courseEntity.setExpectedEndDate(courseAddRequest.getExpectedEndDate());
            courseEntity.setCoveredModules(courseAddRequest.getCoveredModules());
            courseEntity.setTotalNoOfModules(courseAddRequest.getTotalNoOfModules());
            courseEntity.setCollege(collegeRepository.findOneById(courseAddRequest.getCollegeId()));
            return collegeCourseRepository.save(courseEntity);
        } else {
            throw new CollegeExceptions("Unauthorized College");
        }
    }

    @Override
    public void markSubjectClosed(Long collegeId, Long courseId) {
        CourseEntity courseEntity = collegeCourseRepository.findOneById(courseId);
        if(collegeId==courseEntity.getCollege().getId()){
            courseEntity.setActive(false);
            collegeCourseRepository.save(courseEntity);
        }else{
            throw new CollegeCourseException("Not Authorized");
        }
    }

    @Override
    public CourseEntity updateCourseDetails(Long collegeId, Long courseId, CourseEntity courseEntity) {
        CourseEntity course = collegeCourseRepository.findOneById(courseId);
        if (course == null) {
            throw new FacultyCertificateException("Course Not Found");
        } else if(collegeId==course.getCollege().getId()) {
            course.setSubject(courseEntity.getSubject());
            course.setSubjectCode(courseEntity.getSubjectCode());
            course.setPresentDate(courseEntity.getPresentDate());
            course.setStartDate(courseEntity.getStartDate());
            course.setExpectedEndDate(courseEntity.getExpectedEndDate());
            course.setCoveredModules(courseEntity.getCoveredModules());
            course.setTotalNoOfModules(courseEntity.getTotalNoOfModules());
        }
        throw  new CollegeCourseException("Not Authorized");
    }
}
