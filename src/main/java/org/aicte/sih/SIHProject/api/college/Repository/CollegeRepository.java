package org.aicte.sih.SIHProject.api.college.Repository;


import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<CollegeEntity, Long> {
    CollegeEntity findOneByUin(String uin);
    CollegeEntity findOneById(Long id);
    long countByAicteAffiliationNumber(String data);
    long countByUniversityRegistrationNumber(String data);
    long countByEmail(String data);
    long countByPhone(String data);

}
