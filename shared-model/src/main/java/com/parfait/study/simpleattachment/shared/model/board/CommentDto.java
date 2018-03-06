package com.parfait.study.simpleattachment.shared.model.board;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String email;
    private String body;
}
