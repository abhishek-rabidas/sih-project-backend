package org.aicte.sih.SIHProject.api.jobs.dao;

import org.aicte.sih.SIHProject.api.faculty.dto.entities.Faculty;
import org.aicte.sih.SIHProject.api.jobs.dto.Entity.AppliedJob;
import org.aicte.sih.SIHProject.api.jobs.dto.Entity.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppliedJobRepository extends JpaRepository<AppliedJob, Long> {
    List<JobPosting> findAllAppliedPostByFaculty(Faculty faculty);
}
