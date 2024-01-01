package com.minh.zingmp3.repositories;

import com.minh.zingmp3.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    public List<Artist> findTopByFullNameContainingIgnoreCase(String name, int limit);


}