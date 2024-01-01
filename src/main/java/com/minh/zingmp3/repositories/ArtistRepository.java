package com.minh.zingmp3.repositories;

import com.minh.zingmp3.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    @Query("select a from Artist a where a.fullName like %?1% ")
    public List<Artist> findTopByFullNameContainingIgnoreCase(String name,@Param("limit") int limit);


}