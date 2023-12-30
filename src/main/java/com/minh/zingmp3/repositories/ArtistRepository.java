package com.minh.zingmp3.repositories;

import com.minh.zingmp3.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

}