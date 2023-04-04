package com.blog.app.registration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.appuser.UserModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("api/v1/registration")
@RestController
public class RegistrationContoller {

    private final RegistrationService registrationService;

    @PostMapping
    public String registerUser(@RequestBody RegistrationModel userModel) {
        return registrationService.registerUser(userModel);
    }

    @GetMapping(path = "/confirm")
    public String confirmToken(@RequestParam String token) {
        return registrationService.confirmToken(token);
    }

    @GetMapping
    public void homepage() {

    }
}
