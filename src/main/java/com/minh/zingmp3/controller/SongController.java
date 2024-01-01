package com.minh.zingmp3.controller;

import com.minh.zingmp3.enums.Country;
import com.minh.zingmp3.model.Album;
import com.minh.zingmp3.model.Song;
import com.minh.zingmp3.response.GetAllSongResponse;
import com.minh.zingmp3.services.FileUpload;
import com.minh.zingmp3.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/v1/songs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SongController {
    @Autowired
    private SongService songService;
    @Autowired
    private FileUpload fileUpload;
    @GetMapping("/all")
    private ResponseEntity<?> findAll(){
        List<Song> songs = songService.findAllSong();
        if(songs.isEmpty()){
            return ResponseEntity.ofNullable("empty");
        }else{
            return ResponseEntity.ok(songs);
        }
    }
    @GetMapping("/paging/{id}")
    public ResponseEntity<?> getSongsPaging(@PathVariable("id") int currentPage){

        Page<Song> songPage=songService.findAllSong(currentPage-1,10,"id","asc");
        int totalPages=songPage.getTotalPages();
        List<Integer> pageNumbers=new ArrayList<>();
        if(totalPages>0){
             pageNumbers= IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());

        }

        return ResponseEntity.ok(new GetAllSongResponse(songPage,totalPages,pageNumbers));

    }
    @GetMapping
    private Optional<?> findAllSong(){
        return Optional.of(songService.findTopSong());
    }
    @GetMapping("/{country}")
    private Optional<?> findSongByArtistCountry(@PathVariable("country") String country){
        Country country1=Country.valueOf(country);
        return Optional.of(songService.getSongByArtistCountry(country1));
    }

//    @PostMapping
//    private Optional<?> addSong(@RequestParam String name, @RequestParam MultipartFile image) throws IOException {
//        String urlImage = fileUpload.uploadFile(image);
//        Song song = new Song(name, urlImage);
//        return Optional.of(songService.addSong(song));
//    }


    @PostMapping
    private Optional addSong(@RequestParam String name, @RequestParam MultipartFile image, @RequestParam MultipartFile mp3, @RequestParam long idAlbum, @RequestParam(name = "idArtist[]") long[] idArtist) throws Exception {
        String urlImage = fileUpload.uploadFile(image);
        String urlMp3 = fileUpload.uploadFile(mp3);
        Song song=new Song(name,urlImage,urlMp3,new ArrayList<>(),new Album());
        return Optional.of(songService.addSong1(song,idArtist,idAlbum));
    }


    @GetMapping("/album/{id}")
    private Optional<?> findSongByAlbumId(@PathVariable("id") long id){
        return Optional.of(songService.getSongsByAlbum_Id(id));
    }

}
