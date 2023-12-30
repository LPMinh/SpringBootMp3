package com.minh.zingmp3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minh.zingmp3.enums.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Artist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String fullName;
    @Column
    private String img;
    @JsonIgnore
    @OneToMany(mappedBy = "artist",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Album> albums=new HashSet<>();
    @Enumerated(EnumType.STRING)
    private Country country;

    public Artist(String fullName, String img, Set<Album> albums, Country country) {
        this.fullName = fullName;
        this.img = img;
        this.country = country;
        this.albums = albums;
    }

    public Artist(long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
}
