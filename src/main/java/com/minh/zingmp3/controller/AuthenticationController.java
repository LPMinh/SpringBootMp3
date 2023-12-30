package com.minh.zingmp3.controller;

import com.minh.zingmp3.config.AuthenticationResponse;
import com.minh.zingmp3.request.AuthRequest;
import com.minh.zingmp3.request.RegisterRequest;
import com.minh.zingmp3.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authentication(
            @RequestBody AuthRequest request){
        return ResponseEntity.ok(authService.authentication(request));

    }

}
