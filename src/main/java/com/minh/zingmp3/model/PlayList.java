package com.minh.zingmp3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "playlist",fetch = FetchType.LAZY)
    private List<PlaylistSong> playlistSongs=new ArrayList<>();

    @Column
    private String name;
    @Column
    private LocalDate date;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;
    @JsonIgnore
    @OneToMany(mappedBy = "playlist",cascade = CascadeType.ALL)
    private List<CategoryPlaylist> categoryPlaylists=new ArrayList<>();

    @JsonProperty("songs")
    public List<Song> getSong(){
        List<Song> songs=new ArrayList<>();
        for (PlaylistSong playlistSong:playlistSongs){
            songs.add(playlistSong.getSong());
        }
        return songs;

    }

    @JsonProperty("image")
    public String getImageFromFirstSong(){
        if(playlistSongs.size()==0){
            return "https://i.ytimg.com/vi/1vrEljMfXYo/maxresdefault.jpg";
        }
        return playlistSongs.get(0).getSong().getImg();
    }
    public PlayList(List<PlaylistSong> playlistSongs, String name, LocalDate date, User user) {
        this.playlistSongs = playlistSongs;
        this.name = name;
        this.date = date;
        this.user = user;
    }

}
