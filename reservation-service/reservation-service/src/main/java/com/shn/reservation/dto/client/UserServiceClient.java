package com.shn.reservation.dto.client;

import com.shn.reservation.dto.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-management", path = "/api/user/")
public interface UserServiceClient {

    @GetMapping("/{id}")
    ResponseEntity<UserResponse> getUserById(@PathVariable Long id);
}
