package com.minh.zingmp3.response;

import com.minh.zingmp3.model.Album;
import com.minh.zingmp3.model.Artist;
import com.minh.zingmp3.model.PlayList;
import com.minh.zingmp3.model.Song;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRespone {
    private List<Song> songs;
    private List<PlayList> playLists;
    private List<Album> albums;
    private List<Artist> artists;
}
