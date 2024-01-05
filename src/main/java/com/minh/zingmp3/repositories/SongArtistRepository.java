package com.minh.zingmp3.repositories;

import com.minh.zingmp3.model.Song;
import com.minh.zingmp3.model.SongArtist;
import com.minh.zingmp3.pk.PKSongArtist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SongArtistRepository extends JpaRepository<SongArtist, PKSongArtist> {

    @Query("select s from SongArtist s where s.artist.id = ?1 order by s.song.releaseDate desc")
    public List<Song> findTopSongsByArtist_Id(long id);
}