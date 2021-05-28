package com.zti.yerbastore.controller;

import com.zti.yerbastore.exception.ForbiddenException;
import com.zti.yerbastore.model.User;
import com.zti.yerbastore.model.request.UserCredentials;
import com.zti.yerbastore.model.response.Token;
import com.zti.yerbastore.security.SecretKeyGenerator;
import com.zti.yerbastore.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequiredArgsConstructor
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(path = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Token authenticate(@RequestBody UserCredentials userCredentials) {
        User user = userService.findByEmail(userCredentials.getEmail());

        if (passwordEncoder.matches(userCredentials.getPassword(), user.getPassword())) {
            String token = Jwts.builder()
                    .setSubject(user.getEmail())
                    .claim("email", user.getEmail())
                    .claim("role", user.getRole())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                    .signWith(SignatureAlgorithm.HS256, SecretKeyGenerator.getSecretKeyBytes())
                    .compact();

            return Token.builder().token(token).build();
        } else {
            throw new ForbiddenException("Invalid credentials!");
        }
    }

}
