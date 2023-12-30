package com.minh.zingmp3.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayListRequest {
    private String name;
    private String email;
}
