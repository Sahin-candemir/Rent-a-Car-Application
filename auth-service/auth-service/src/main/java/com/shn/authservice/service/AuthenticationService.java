package com.shn.authservice.service;

import com.shn.authservice.dto.AuthRequest;
import com.shn.authservice.dto.AuthResponse;
import com.shn.authservice.dto.RegisterRequest;
import com.shn.authservice.dto.TokenDto;
import com.shn.authservice.model.Role;
import com.shn.authservice.model.User;
import com.shn.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role(Role.USER)
                .build();
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
        User user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow();

        String jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void validate(TokenDto tokenDto) {
        final String username =  jwtService.extractUsername(tokenDto.getToken());
        UserDetails userDetails =userDetailsService.loadUserByUsername(username);

        if (!jwtService.isTokenValid(tokenDto.getToken(), userDetails)){
            throw new RuntimeException("Token is not valid");
        }


    }
}
