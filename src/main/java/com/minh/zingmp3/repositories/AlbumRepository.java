package com.minh.zingmp3.repositories;

import com.minh.zingmp3.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findAllByArtistId(long id);
    Album findAlbumById(long id);


    @Query(value = "SELECT a FROM Album a order by a.releaseDate desc " )
    List<Album> findTopAllAlbums(@Param("limit") int limit);
}