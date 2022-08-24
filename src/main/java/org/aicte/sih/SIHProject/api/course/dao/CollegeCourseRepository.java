package org.aicte.sih.SIHProject.api.course.dao;


import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.api.course.dto.Entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollegeCourseRepository extends JpaRepository<CourseEntity,Long> {
    CourseEntity findOneById(Long id);

    long countBySubjectCode(String data);

    List<CourseEntity> findAllByCollege(CollegeEntity college);

}
