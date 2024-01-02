package com.minh.zingmp3.services;

import com.minh.zingmp3.model.PlayList;
import com.minh.zingmp3.model.PlaylistSong;
import com.minh.zingmp3.model.Song;
import com.minh.zingmp3.model.User;
import com.minh.zingmp3.request.RequestAddSongToPlayList;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayListService {
    @Autowired
    private com.minh.zingmp3.repositories.PlayListRepository playListRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SongService songService;



    public com.minh.zingmp3.model.PlayList create(com.minh.zingmp3.request.PlayListRequest playListRequest) {
        User user=userService.findByEmail(playListRequest.getEmail());
        PlayList playlist=new PlayList(new ArrayList<>(),playListRequest.getName(), LocalDate.now(),user);
        return playListRepository.save(playlist);

    }
    public List<PlayList> findAll(){
        return playListRepository.findAll();
    }

    public void addSongToPlayList(RequestAddSongToPlayList requestAddSongToPlayList) {
        PlayList playList = playListRepository.findById(requestAddSongToPlayList.getIdPlayList()).orElse(null);
        if(playList==null) return;
        Song song = songService.findSongById(requestAddSongToPlayList.getIdSong());
        if(song==null) return;
        PlaylistSong playlistSong = new PlaylistSong(playList,song);
        playList.getPlaylistSongs().add(playlistSong);
        playListRepository.save(playList);
    }
    public PlayList findById(Long id){
        return playListRepository.findById(id).orElse(null);
    }

    public List<PlayList> findByUserId(Long id) {
        return playListRepository.getPlayListsByUserId(id);
    }

    public Optional<PlayList> findPlayListById(long idPlayList) {
        return playListRepository.findById(idPlayList);
    }

    public List<PlayList> search(String keyword) {
        return playListRepository.findTopByPlayListNameContainingIgnoreCase(keyword, 10);
    }
    //delete
    public boolean deletePlayList(long id){
        PlayList playList = playListRepository.findById(id).orElse(null);
        if(playList==null) return false;
        playListRepository.delete(playList);
        return true;
    }
}
