package com.minh.zingmp3.services;

import com.minh.zingmp3.model.Album;
import com.minh.zingmp3.model.Artist;
import com.minh.zingmp3.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "albumbyid", key = "#id")
    public Album findAlbumById(Long id){
        return albumRepository.findById(id).orElse(null);
    }
    @Cacheable(value = "allalbum", key = "#root.method.name")
    public List<Album> findAllAlbum(){
        return albumRepository.findAll();
    }
    @Cacheable(value = "topalbumnew", key = "#root.method.name")
    public List<Album> getTopAlbumNew(int limit){
        return albumRepository.findTopAllAlbums(limit);
    }
    @Cacheable(value = "albumbyartistid", key = "#id")
    public List<Album> getListAlbumByArtistId(long id){
        return albumRepository.findAllByArtistId(id);
    }
    @Cacheable(value = "albumbyname", key = "#name")
    public List<Album> search(String keyword) {
        return albumRepository.findTopByAlbumByName(keyword, 10);
    }
}
