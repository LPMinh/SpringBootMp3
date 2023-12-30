package com.minh.zingmp3.pk;

import com.minh.zingmp3.model.Artist;
import com.minh.zingmp3.model.Song;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PKSongArtist implements Serializable {
    private Song song;
    private Artist artist;
}
