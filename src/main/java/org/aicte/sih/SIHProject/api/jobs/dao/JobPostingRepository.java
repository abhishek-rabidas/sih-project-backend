package org.aicte.sih.SIHProject.api.jobs.dao;

import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.aicte.sih.SIHProject.api.jobs.dto.Entity.JobPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface JobPostingRepository extends JpaRepository<JobPost, Long> {
    JobPost findOneById(Long id);
    Page<JobPost> findAllByIsOpenTrue(Pageable pageable);
    Page<JobPost> findAllByCollegeAndIsOpenTrue(CollegeEntity college, Pageable pageable);
    Page<JobPost> findAllByCollegeAndIsOpenFalse(CollegeEntity college, Pageable pageable);
}
