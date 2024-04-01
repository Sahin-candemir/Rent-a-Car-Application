package com.shn.user.service;

import com.shn.user.dto.CreateUserRequest;
import com.shn.user.dto.UserResponse;
import com.shn.user.exception.ResourceNotFoundException;
import com.shn.user.model.User;
import com.shn.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    public UserResponse createUser(CreateUserRequest createUserRequest){
        User user = User.builder()
                .username(createUserRequest.getUsername())
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .birthDate(createUserRequest.getBirthDate())
                .emailAddress(createUserRequest.getEmailAddress())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .createdDate(LocalDateTime.now())
                .build();
        User saveUser = userRepository.save(user);

        return mapper.map(saveUser, UserResponse.class);
    }

    public UserResponse getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : "+id));

        return mapper.map(user, UserResponse.class);
    }
}
