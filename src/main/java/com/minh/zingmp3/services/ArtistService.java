package com.minh.zingmp3.services;

import com.minh.zingmp3.model.Artist;
import com.minh.zingmp3.repositories.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArtistService {
    @Autowired
    private final ArtistRepository artistRepository;

    public boolean addArtist(Artist artist) {
       return artistRepository.save(artist)!=null;
    }
    public Optional<Artist> findArtistById(Long id){
        return artistRepository.findById(id);
    }
    public List<Artist> findAllArtist(){
        return artistRepository.findAll();
    }


    public List<Artist> search(String keyword) {
        return artistRepository.findTopByFullNameContainingIgnoreCase(keyword, 10);
    }
    public List<Artist> getTopArtist(int limit){
        return artistRepository.getTopArtist(limit);
    }
}
