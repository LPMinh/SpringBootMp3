package com.minh.zingmp3.repositories;

import com.minh.zingmp3.enums.Country;
import com.minh.zingmp3.model.Song;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    @Query("select s from Song s join s.songArtists sa join sa.artist a  where a.country = ?1 order by s.releaseDate desc")
    public List<Song> findTopSongsByArtistCountry(Country country, Pageable pageable);
    public List<Song> getSongsByAlbum_Id(long id);
    @Query("select s from Song s where s.name like %?1% ")
    public List<Song> findTopSongByNameContainingIgnoreCase(String name,@Param("limit") int limit);




}