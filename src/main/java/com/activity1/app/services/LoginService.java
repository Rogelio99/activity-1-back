package com.activity1.app.services;

import com.activity1.app.web.models.LoginModel;
import com.activity1.configuration.JWTAuthorizationFilter;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.Key;

@Log4j2
@Service
public class LoginService {
    private Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public LoginModel login(LoginModel loginModel) {
        String token = JWTAuthorizationFilter.getJWTToken(loginModel.getUsername());
        loginModel.setToken(token);
        return loginModel;
    }
}
