package com.shn.authservice.controller;

import com.shn.authservice.dto.AuthRequest;
import com.shn.authservice.dto.AuthResponse;
import com.shn.authservice.dto.RegisterRequest;
import com.shn.authservice.dto.TokenDto;
import com.shn.authservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest){
        return new ResponseEntity<>(authenticationService.register(registerRequest), HttpStatus.OK);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest){
        return new ResponseEntity<>(authenticationService.authenticate(authRequest), HttpStatus.OK);
    }
    @PostMapping("/validate")
    public ResponseEntity<String> validate(@RequestBody TokenDto tokenDto){
        authenticationService.validate(tokenDto);
        return new ResponseEntity<>("token is valid", HttpStatus.OK);
    }
}
