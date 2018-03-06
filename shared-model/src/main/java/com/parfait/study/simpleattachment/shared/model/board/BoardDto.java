package com.parfait.study.simpleattachment.shared.model.board;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.parfait.study.simpleattachment.shared.model.attachment.CommentsAttachable;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardDto implements CommentsAttachable {
    private Long id;
    private String title;
    private String content;

    private List<CommentDto> comments;

    @Override
    public long getCommentsAttachableTargetId() {
        return id;
    }

    @Override
    public void attachComments(List<CommentDto> comments) {
        this.comments = comments;
    }
}
