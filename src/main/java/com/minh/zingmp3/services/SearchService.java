package com.minh.zingmp3.services;

import com.minh.zingmp3.response.SearchRespone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;
    private final PlayListService playListService;

    public SearchRespone search(String keyword) {
        SearchRespone searchRespone = new SearchRespone();
        searchRespone.setSongs(songService.search(keyword));
        searchRespone.setAlbums(albumService.search(keyword));
        searchRespone.setArtists(artistService.search(keyword));
        searchRespone.setPlayLists(playListService.search(keyword));
        return searchRespone;
    }
}
