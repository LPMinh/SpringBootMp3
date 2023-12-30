package com.minh.zingmp3.controller;

import com.minh.zingmp3.enums.Country;
import com.minh.zingmp3.model.Artist;
import com.minh.zingmp3.services.ArtistService;
import com.minh.zingmp3.services.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/artists")
@CrossOrigin(origins = "https://musiclpm-e440eeb9818d.herokuapp.com")
public class ArtistController {
    @Autowired
    private ArtistService artistService;
    @Autowired
    private  FileUpload fileUpload;


    private org.slf4j.Logger logger= org.slf4j.LoggerFactory.getLogger(ArtistController.class);


    @PostMapping
    private Optional<?> addArtist(@RequestParam String name, @RequestParam MultipartFile avatar,@RequestParam String country) throws IOException {
        Country country1=Country.valueOf(country);
        String urlAtvatar = fileUpload.uploadFile(avatar);
        logger.info("url avatar: "+urlAtvatar);
        return Optional.of(artistService.addArtist(new Artist(name,urlAtvatar,new HashSet<>(),country1)));
    }
    @GetMapping
    private Optional<?> findAllArtist(){
        return Optional.of(artistService.findAllArtist());
    }
    @GetMapping("/{id}")
    private Optional<?> findArtistById(@PathVariable Long id){
        return artistService.findArtistById(id);
    }

}
