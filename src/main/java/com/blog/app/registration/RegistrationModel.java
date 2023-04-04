package com.blog.app.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationModel {

    private String firstname;
    private String lastname;
    private String email;
    private String password;

}
