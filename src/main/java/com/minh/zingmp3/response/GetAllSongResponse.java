package com.minh.zingmp3.response;

import com.minh.zingmp3.model.Song;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor


public class GetAllSongResponse {
    Page<Song> songPage;
    int totalPages;
    List<Integer> pageNumbers;

    public GetAllSongResponse(Page<Song> songPage, int totalPages, List<Integer> pageNumbers) {
        this.songPage = songPage;
        this.totalPages = totalPages;
        this.pageNumbers = pageNumbers;
    }
}
