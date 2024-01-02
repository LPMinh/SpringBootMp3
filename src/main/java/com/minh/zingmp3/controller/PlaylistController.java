package com.minh.zingmp3.controller;

import com.minh.zingmp3.model.PlayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/playlists")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlaylistController {
    @Autowired
    private  com.minh.zingmp3.services.PlayListService playlistService;
    @GetMapping
    public List<PlayList> findAll(){
        return playlistService.findAll();
    }

    @PostMapping
    public com.minh.zingmp3.model.PlayList create(@RequestBody com.minh.zingmp3.request.PlayListRequest playListRequest){
        return playlistService.create(playListRequest);
    }
    @GetMapping("/{id}")
    public com.minh.zingmp3.model.PlayList findById(@PathVariable Long id){
        return playlistService.findById(id);
    }

    @GetMapping("/user/{id}")
    public List<PlayList> findByUserId(@PathVariable long id){
        return playlistService.findByUserId(id);
    }
    @PostMapping("/add-song")
    public void addSongToPlayList(@RequestBody com.minh.zingmp3.request.RequestAddSongToPlayList requestAddSongToPlayList){
        playlistService.addSongToPlayList(requestAddSongToPlayList);
    }
    @DeleteMapping("/{id}")
    public void deletePlayList(@PathVariable long id){
        playlistService.deletePlayList(id);
    }



}
