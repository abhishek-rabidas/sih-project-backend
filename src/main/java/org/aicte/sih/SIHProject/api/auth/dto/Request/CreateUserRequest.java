package org.aicte.sih.SIHProject.api.auth.dto.Request;

import lombok.Getter;

@Getter
public class CreateUserRequest {
    private String name;
    private String password;
    private String emailAddress;
    private String phoneNumber;
    private String role;
}
