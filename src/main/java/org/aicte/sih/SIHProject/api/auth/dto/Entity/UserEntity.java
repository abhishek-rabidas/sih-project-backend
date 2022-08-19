package org.aicte.sih.SIHProject.api.auth.dto.Entity;

import lombok.Getter;
import lombok.Setter;
import org.aicte.sih.SIHProject.api.auth.common.UserRole;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER")
@Getter
@Setter
public class UserEntity extends AbstractPersistable<Long> {
    private String name;
    private String password;
    private String emailAddress;
    private BigInteger phoneNumber;
    private LocalDateTime creationTimestamp;
    private LocalDateTime updatedOn;
    private boolean status;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private LocalDateTime lastLoginTime;
    private String profilePicture;
}
