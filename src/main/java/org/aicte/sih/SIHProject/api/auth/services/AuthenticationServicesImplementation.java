package org.aicte.sih.SIHProject.api.auth.services;

import org.aicte.sih.SIHProject.api.auth.dao.UserRepository;
import org.aicte.sih.SIHProject.api.auth.dto.Entity.UserEntity;
import org.aicte.sih.SIHProject.api.auth.dto.Request.CreateUserRequest;
import org.aicte.sih.SIHProject.api.auth.dto.Response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Service
public class AuthenticationServicesImplementation implements AuthenticationServices {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse registerUser(CreateUserRequest userRequest) {
        UserEntity user = new UserEntity();
        user.setName(userRequest.getName());
        user.setEmailAddress(userRequest.getEmailAddress());
        user.setPhoneNumber(new BigInteger(userRequest.getPhoneNumber()));
        user.setCreationTimestamp(LocalDateTime.now());
        user.setUpdatedOn(LocalDateTime.now());
        user.setStatus(true);
        user.setLastLoginTime(LocalDateTime.now());
        user.setPassword(userRequest.getPassword()); //TODO encrypt password
        user.setProfilePicture(null); //TODO store image in AWS S3 and add the base URI
        return new UserResponse(userRepository.save(user));
    }
}
