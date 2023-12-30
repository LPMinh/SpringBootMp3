package com.minh.zingmp3.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data

@NoArgsConstructor
public class RequestAddSongToPlayList {
    private long idSong;
    private long idPlayList;
}
