package org.aicte.sih.SIHProject.api.auth.dto.Response;

import lombok.Getter;
import lombok.Setter;
import org.aicte.sih.SIHProject.api.auth.dto.Entity.UserEntity;

import java.time.LocalDateTime;

@Setter
@Getter
public class UserResponse {
    private String name;
    private String emailAddress;
    private String phoneNumber;
    private LocalDateTime creationTimestamp;
    private LocalDateTime updatedOn;
    private boolean status;
    private String role;
    private LocalDateTime lastLoginTime;
    private String profilePicture;

    public UserResponse(UserEntity user) {
        this.name = user.getName();
        this.emailAddress = user.getEmailAddress();
        this.phoneNumber = user.getPhoneNumber().toString();
        this.creationTimestamp = user.getCreationTimestamp();
        this.updatedOn = user.getUpdatedOn();
        this.status = user.isStatus();
        this.role = user.getRole().name();
        this.lastLoginTime = user.getLastLoginTime();
        this.profilePicture = user.getProfilePicture();
    }
}
