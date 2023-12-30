package com.minh.zingmp3.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAddPlaylistToCategory {
    private long categoryId;
    private long playlistId;
}
