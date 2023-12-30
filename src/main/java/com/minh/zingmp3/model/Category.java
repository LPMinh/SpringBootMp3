package com.minh.zingmp3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category",fetch = FetchType.LAZY)
    private List<CategoryPlaylist> categoryPlaylists=new ArrayList<>();

    public Category(String name, List<CategoryPlaylist> categoryPlaylists) {
        this.name = name;
        this.categoryPlaylists = categoryPlaylists;
    }
    @JsonProperty("playlists")
    public List<PlayList> getPlaylists(){
        List<PlayList> playLists=new ArrayList<>();
        for (CategoryPlaylist categoryPlaylist:categoryPlaylists){
            System.out.println(categoryPlaylist.getPlaylist().getName());
            playLists.add(categoryPlaylist.getPlaylist());
        }
        return playLists;
    }
}
