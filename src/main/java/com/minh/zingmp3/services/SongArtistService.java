package com.minh.zingmp3.services;

import com.minh.zingmp3.model.Song;
import com.minh.zingmp3.model.SongArtist;
import com.minh.zingmp3.repositories.SongArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongArtistService {
    @Autowired
    private SongArtistRepository songArtistRepository;
    @Async
    public boolean addSongArtist(SongArtist songArtist){
        return songArtistRepository.save(songArtist)!=null;
    }

    public List<Song> getSongNewByArtistId(long id){
        return songArtistRepository.findTopSongsByArtist_Id(id);
    }
}
