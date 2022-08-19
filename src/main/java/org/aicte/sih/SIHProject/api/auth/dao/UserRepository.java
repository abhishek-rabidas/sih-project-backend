package org.aicte.sih.SIHProject.api.auth.dao;

import org.aicte.sih.SIHProject.api.auth.dto.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
