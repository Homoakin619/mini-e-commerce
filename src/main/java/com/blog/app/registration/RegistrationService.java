package com.blog.app.registration;

import org.springframework.stereotype.Service;

import com.blog.app.appuser.AppUserService;
import com.blog.app.appuser.UserModel;
import com.blog.app.security.token.TokenModel;
import com.blog.app.security.token.TokenService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistrationService {

    private final AppUserService appUserService;
    private final TokenService tokenService;

    public String registerUser(RegistrationModel userModel) {

        return appUserService.createUser(new UserModel(
                userModel.getFirstname(), userModel.getLastname(), userModel.getEmail(), userModel.getPassword()));

    }

    @Transactional
    public String confirmToken(String token) {
        TokenModel tokenInstance = tokenService.confirmToken(token);
        tokenInstance.getUserModel().setEnabled(true);

        return "Token successfully confirmed";
    }

}
