package org.aicte.sih.SIHProject.api.auth.services;

import org.aicte.sih.SIHProject.api.auth.dto.Request.CreateUserRequest;
import org.aicte.sih.SIHProject.api.auth.dto.Response.UserResponse;

public interface AuthenticationServices {
    UserResponse registerUser(CreateUserRequest userRequest);
}
