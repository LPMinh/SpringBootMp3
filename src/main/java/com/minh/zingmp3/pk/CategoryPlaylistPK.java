package com.minh.zingmp3.pk;

import com.minh.zingmp3.model.Category;
import com.minh.zingmp3.model.PlayList;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CategoryPlaylistPK implements Serializable {
    private Category category;
    private PlayList playlist;
}
