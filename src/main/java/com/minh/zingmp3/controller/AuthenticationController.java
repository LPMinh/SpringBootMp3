package com.minh.zingmp3.controller;

import com.minh.zingmp3.config.AuthenticationResponse;
import com.minh.zingmp3.model.User;
import com.minh.zingmp3.request.AuthRequest;
import com.minh.zingmp3.request.RegisterRequest;
import com.minh.zingmp3.services.AuthService;
import com.minh.zingmp3.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "https://musiclpm-e440eeb9818d.herokuapp.com")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/users")
    public List<User> getAllUser(){
        return userService.findAllUser();
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id){
        userService.deleteUser(id);
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request) throws IOException {
        User user = userService.findByEmail(request.getEmail());
        if(user!=null){
            return ResponseEntity.badRequest().body("Email is exist");
        }
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authentication(
            @RequestBody AuthRequest request){
        return ResponseEntity.ok(authService.authentication(request));

    }

}
