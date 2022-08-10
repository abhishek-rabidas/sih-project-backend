package org.aicte.sih.SIHProject.api.jobs.dao;

import org.aicte.sih.SIHProject.api.jobs.dto.Entity.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    JobPosting findOneById(Long id);
}
