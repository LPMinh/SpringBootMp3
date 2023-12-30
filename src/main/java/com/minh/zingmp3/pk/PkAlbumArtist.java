package com.minh.zingmp3.pk;

import com.minh.zingmp3.model.Album;
import com.minh.zingmp3.model.Artist;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PkAlbumArtist implements Serializable {
    private Album album;
    private Artist artist;
}
