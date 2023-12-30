package com.minh.zingmp3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minh.zingmp3.pk.CategoryPlaylistPK;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CategoryPlaylistPK.class)
public class CategoryPlaylist implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_category")
    @JsonIgnore
    private Category category;
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_playlist")
    @JsonIgnore
    private PlayList playlist;
}
