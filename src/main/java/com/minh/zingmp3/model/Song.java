package com.minh.zingmp3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.minh.zingmp3.enums.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "songs")

public class Song implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String img;
    @Column
    private String mp3;
    @Column
    private LocalDate releaseDate;
    @OneToMany(mappedBy = "song",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SongArtist> songArtists=new ArrayList<SongArtist>();

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_album")
    private Album album;
    @OneToMany(mappedBy = "song")
    @JsonIgnore
    private List<PlaylistSong> playlistSongs=new ArrayList<>();
    private String artistsName;

    public Song(String name, String img, String mp3, List<SongArtist> songArtists, Album album) {
        this.name = name;
        this.img = img;
        this.mp3 = mp3;
        this.songArtists = songArtists;
        this.album = album;
        this.releaseDate=LocalDate.now();

    }
    @JsonProperty("artists")
    public Artist[] getArtistsName() {
//
        Artist[] artists=new Artist[songArtists.size()];
        for (int i = 0; i < songArtists.size(); i++) {
            artists[i]=new Artist(songArtists.get(i).getArtist().getId(),songArtists.get(i).getArtist().getFullName());
        }

        return artists;
    }


}
