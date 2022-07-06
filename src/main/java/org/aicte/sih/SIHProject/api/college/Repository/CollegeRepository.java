package org.aicte.sih.SIHProject.api.college.Repository;


import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollegeRepository extends JpaRepository<CollegeEntity, Long> {

    Optional<CollegeEntity> findCollegeByUIN(String uin);
}
