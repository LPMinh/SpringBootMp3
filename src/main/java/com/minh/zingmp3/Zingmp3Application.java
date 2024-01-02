package com.minh.zingmp3;

import com.cloudinary.Cloudinary;
import com.minh.zingmp3.enums.Country;
import com.minh.zingmp3.model.*;
import com.minh.zingmp3.repositories.AlbumRepository;
import com.minh.zingmp3.repositories.ArtistRepository;
import com.minh.zingmp3.repositories.SongArtistRepository;
import com.minh.zingmp3.repositories.SongRepository;
import com.minh.zingmp3.request.RegisterRequest;
import com.minh.zingmp3.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@SpringBootApplication
//@EnableCaching
public class Zingmp3Application {
    private final String CLOUD_NAME = "dbxogj6oe";
    private final String API_KEY = "967651379553858";
    private final String API_SECRET = "SoImI0NrKq5nMWIJ6kaEnL11cgw";
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private SongArtistRepository songArtistRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AuthService authService;


    public static void main(String[] args) {
        SpringApplication.run(Zingmp3Application.class, args);
    }

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);
        return new Cloudinary(config);
    }

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            RegisterRequest registerRequest = new RegisterRequest("admin","adminh@gmail.com","123456","admin","ADMIN","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9jvCXelhOqokaAXbRDLoJ4aX2SAoT7S-mfw&usqp=CAU");

            authService.register(registerRequest);
//            RegisterRequest registerRequest1 = new RegisterRequest("user","user@gmail.com","123456","user","USER","https://nemliena.com.vn/wp-content/uploads/2023/08/Chu-Meo-Tang-Hoa-Dep.jpg");
//            authService.register(registerRequest1);

        };
    }
}



