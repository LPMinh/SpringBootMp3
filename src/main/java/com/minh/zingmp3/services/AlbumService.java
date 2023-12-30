package com.minh.zingmp3.services;

import com.minh.zingmp3.model.Album;
import com.minh.zingmp3.model.Artist;
import com.minh.zingmp3.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private ArtistService artistService;

    public Optional<Album> addAlbum(Album album, long idArtist) {
        Artist artist = artistService.findArtistById(idArtist).orElse(null);
        if(artist==null) return null;
        album.setArtist(artist);
       return Optional.of(albumRepository.save(album));
    }

    public boolean addArtistToAlbum(long idAlbum, long idArtist) {
        Album album = albumRepository.findById(idAlbum).orElse(null);
        if(album==null) return false;
        Artist artist = artistService.findArtistById(idArtist).orElse(null);
        album.setArtist(artist);
       if(albumRepository.save(album)!=null){
              artist.getAlbums().add(album);
              artistService.addArtist(artist);
              return true;
       }
         return false;
    }

    public Album findAlbumById(Long id){
        return albumRepository.findById(id).orElse(null);
    }
    public List<Album> findAllAlbum(){
        return albumRepository.findAll();
    }

    public List<Album> getTopAlbumNew(int limit){
        return albumRepository.findTopAllAlbums(limit);
    }
    public List<Album> getListAlbumByArtistId(long id){
        return albumRepository.findAllByArtistId(id);
    }
}
