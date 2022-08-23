package org.aicte.sih.SIHProject.api.course.dao;


import org.aicte.sih.SIHProject.api.course.dto.Entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyCourseRepository extends JpaRepository<CourseEntity,Long> {
}
