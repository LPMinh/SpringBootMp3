package com.minh.zingmp3.services;

import com.minh.zingmp3.enums.Country;
import com.minh.zingmp3.model.Album;
import com.minh.zingmp3.model.Artist;
import com.minh.zingmp3.model.Song;
import com.minh.zingmp3.model.SongArtist;
import com.minh.zingmp3.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private SongArtistService songArtistService;



    public Page<Song> findAllSong(int pageNo,int pageSize,String sortBy,String sortDirection){
        Sort sort=Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        return songRepository.findAll(pageable);

    }
    public boolean addSong(Song song) {
       return songRepository.save(song)!=null;
    }
    public Song findSongById(Long id){
        return songRepository.findById(id).orElse(null);
    }
    public List<Song> findAllSong(){
        return songRepository.findAll();
    }

    public List<Song> findTopSong(){
        return songRepository.findAll(PageRequest.of(0, 9)).getContent();
    }

    public Song addSong1(Song song, long [] idArtist, long idAlbum){
        Album album = albumService.findAlbumById(idAlbum);
        song.setAlbum(album);
        for (long id : idArtist) {
            Artist artist = artistService.findArtistById(id).get();
            SongArtist songArtist = new SongArtist();
            songArtist.setArtist(artist);
            songArtist.setSong(song);
            song.getSongArtists().add(songArtist);
        }
        return  songRepository.save(song);
    }

    public List<Song> getSongsByAlbum_Id(long id){
        return songRepository.getSongsByAlbum_Id(id);
    }

    public List<Song> getSongByArtistCountry(Country country){
        return songRepository.findTopSongsByArtistCountry(country, Pageable.ofSize(6));
    }


    public List<Song> search(String keyword) {
        return songRepository.findTopSongByNameContainingIgnoreCase(keyword, 10);
    }

    public List<Song> getListSongNewByArtistId(long artistId){
        return songArtistService.getSongNewByArtistId(artistId);
    }
}
