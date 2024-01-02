package com.minh.zingmp3.controller;

import com.minh.zingmp3.model.Album;
import com.minh.zingmp3.services.AlbumService;
import com.minh.zingmp3.services.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/albums")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private FileUpload fileUpload;

    @PostMapping
    private Optional<Album> addAlbum(@RequestParam String name, @RequestParam MultipartFile image,@RequestParam long idArtist) throws IOException {
        String urlImage = fileUpload.uploadFile(image);
        Album album = new Album(name, urlImage);
        return albumService.addAlbum(album,idArtist);
    }

    @GetMapping("/new-album")
    private List<Album> getAllAlbumNew(){
        return albumService.getTopAlbumNew(5);
    }

    @GetMapping("/{id}")
    private Optional<?> findAlbumById(@PathVariable Long id){
        return Optional.ofNullable(albumService.findAlbumById(id));
    }
    @DeleteMapping("/{id}")
    private void deleteAlbum(@PathVariable Long id){
        albumService.deleteAlbum(id);
    }
    @GetMapping
    private Optional<?> findAllAlbum(){
        return Optional.ofNullable(albumService.findAllAlbum());
    }
    @GetMapping("/artist/{id}")
    private Optional<?> getListAlbumByArtistId(@PathVariable Long id){
        return Optional.ofNullable(albumService.getListAlbumByArtistId(id));
    }
}
