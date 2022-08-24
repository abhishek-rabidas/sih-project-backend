package org.aicte.sih.SIHProject.api.course.dto.Request;


import lombok.Getter;
import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.api.faculty.dto.entities.Faculty;

@Getter
public class CourseAddRequest {

    private String Subject;
    private int totalNoOfModules;
    private int coveredModules;
    private String subjectCode;
    private String startDate;
    private String presentDate;
    private String expectedEndDate;
    private Long  collegeId;

}
