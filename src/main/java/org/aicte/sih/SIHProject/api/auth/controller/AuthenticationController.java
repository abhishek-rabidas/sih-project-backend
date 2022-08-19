package org.aicte.sih.SIHProject.api.auth.controller;

import org.aicte.sih.SIHProject.api.auth.dto.Request.CreateUserRequest;
import org.aicte.sih.SIHProject.api.auth.dto.Response.UserResponse;
import org.aicte.sih.SIHProject.api.auth.services.AuthenticationServices;
import org.aicte.sih.SIHProject.commons.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationServices authenticationServices;

    @PostMapping
    public ResponseEntity<APIResponse<UserResponse>> register(@RequestBody CreateUserRequest createUserRequest) {
        APIResponse<UserResponse> response = new APIResponse<>();
        try {
            response.setData(authenticationServices.registerUser(createUserRequest));
            return ResponseEntity.ok(response);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            response.setStatusCode(e.getStatusCode().value());
            response.setMessage(e.getStatusText());
            return ResponseEntity.internalServerError().body(response);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }

    }
}
