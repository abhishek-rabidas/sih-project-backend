package org.aicte.sih.SIHProject.api.jobs.dao;

import org.aicte.sih.SIHProject.api.faculty.dto.entities.Faculty;
import org.aicte.sih.SIHProject.api.jobs.dto.Entity.AppliedJob;
import org.aicte.sih.SIHProject.api.jobs.dto.Entity.JobPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppliedJobRepository extends JpaRepository<AppliedJob, Long> {
    List<AppliedJob> findAllByFaculty(Faculty faculty);
    Page<AppliedJob> findAllByAppliedPost(JobPost jobPost, Pageable pageable);
    @Query(nativeQuery = true, value = "SELECT count(1) FROM applied_job where applied_post_id = :jobPostId AND faculty_id = :facultyId")
    int countByAppliedPostAndFaculty(@Param("jobPostId") long jobPostId, @Param("facultyId") long facultyId);
}
