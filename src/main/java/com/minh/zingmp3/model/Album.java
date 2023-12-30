package com.minh.zingmp3.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Album  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String img;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_artist")
    private Artist artist;
    @Column
    private LocalDate releaseDate;
    @OneToMany(mappedBy = "album",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Song> songs=new HashSet<>();




    public Album(String name, String img) {
        this.name = name;
        this.img = img;
        this.artist=new Artist();
        this.releaseDate=LocalDate.now();
    }


}
