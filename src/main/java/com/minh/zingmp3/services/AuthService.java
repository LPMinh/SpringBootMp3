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

import java.io.IOException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    private final  JWTService jwtService;

    private final  FileUpload fileUpload;

    public AuthenticationResponse register(RegisterRequest request) throws IOException {
        String avatar=fileUpload.uploadFile(request.getAvatar());
        User user= new User(request.getEmail(),passwordEncoder.encode(request.getPassword()),request.getName(), Role.valueOf(request.getRole()),new ArrayList<>(),avatar);
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
