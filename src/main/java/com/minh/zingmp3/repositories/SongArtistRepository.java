package com.minh.zingmp3.repositories;

import com.minh.zingmp3.model.SongArtist;
import com.minh.zingmp3.pk.PKSongArtist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongArtistRepository extends JpaRepository<SongArtist, PKSongArtist> {
}