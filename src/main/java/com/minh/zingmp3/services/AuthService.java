package com.minh.zingmp3.services;


import com.minh.zingmp3.config.AuthenticationResponse;
import com.minh.zingmp3.model.Role;
import com.minh.zingmp3.model.User;
import com.minh.zingmp3.repositories.UserRepository;
import com.minh.zingmp3.request.AuthRequest;
import com.minh.zingmp3.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final  JWTService jwtService;

    private final  FileUpload fileUpload;

    public AuthenticationResponse register(RegisterRequest request) {
        User user= new User(request.getUseName(),request.getEmail(),passwordEncoder.encode(request.getPassword()),request.getName(), Role.valueOf(request.getRole()),new ArrayList<>());
        userRepository.save(user);
        String jsonToken=jwtService.grenerateToken(user);
        return AuthenticationResponse.builder().token(jsonToken).build();

    }

    public AuthenticationResponse authentication(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        User user =userRepository.findByEmail(request.getEmail()).orElseThrow(()->new RuntimeException("User not found"));
        String jsonToken= jwtService.grenerateToken(user);
        return AuthenticationResponse.builder().token(jsonToken).data(user).build();

    }
}
