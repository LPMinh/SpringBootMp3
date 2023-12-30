package com.minh.zingmp3.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private com.minh.zingmp3.repositories.UserRepository userRepository;

    public <Optional> com.minh.zingmp3.model.User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    //findUserByID
    public com.minh.zingmp3.model.User findUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }




}
