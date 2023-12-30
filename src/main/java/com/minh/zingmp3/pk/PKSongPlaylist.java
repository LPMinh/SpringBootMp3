package com.minh.zingmp3.pk;

import com.minh.zingmp3.model.PlayList;
import com.minh.zingmp3.model.Song;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PKSongPlaylist implements Serializable {
    private Song song;
    private PlayList playlist;
}
