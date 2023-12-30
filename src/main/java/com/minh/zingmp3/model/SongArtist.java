package com.minh.zingmp3.model;

import com.minh.zingmp3.pk.PKSongArtist;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@IdClass(PKSongArtist.class)
public class SongArtist implements Serializable {
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "song_id")

    private Song song;
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "artist_id")
    private Artist artist;
}
