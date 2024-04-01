package com.shn.apigateway.client;

import com.shn.apigateway.dto.AuthRequest;
import com.shn.apigateway.dto.AuthResponse;
import com.shn.apigateway.dto.RegisterRequest;
import com.shn.apigateway.dto.TokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "auth-service", path = "/api/auth/")
public interface AuthServiceClient {
    @PostMapping("/register")
    ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest);
    @PostMapping("/authenticate")
    ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest);

    @PostMapping("/validate")
    ResponseEntity<String> validate(@RequestBody TokenDto tokenDto);
}
