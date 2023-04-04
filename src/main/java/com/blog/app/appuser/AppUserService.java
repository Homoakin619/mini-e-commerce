package com.blog.app.appuser;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.app.security.token.TokenModel;
import com.blog.app.security.token.TokenService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return appUserRepository.findUserByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("specified user does not exist"));
    }

    public String createUser(UserModel userModel) {
        boolean userExists = appUserRepository.findUserByEmail(userModel.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalStateException("email is taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(userModel.getPassword());
        userModel.setPassword(encodedPassword);
        appUserRepository.save(userModel);

        String token = UUID.randomUUID().toString();

        tokenService.generateToken(
                new TokenModel(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), userModel));

        return token;
    }

}
