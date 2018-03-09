package com.parfiat.study.simpleattachment.webflux.board.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class Board {

    private Long id;
    private String title;
    private String content;
    private Long writerId;

    public Board(@NonNull String title, @NonNull String content, @NonNull Long writerId) {
        this.title = title;
        this.content = content;
        this.writerId = writerId;
    }
}


