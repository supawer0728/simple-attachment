package com.parfait.study.simpleattachment.shared.model.attachment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parfait.study.simpleattachment.shared.model.board.CommentDto;

import java.util.List;

public interface CommentsAttachable {
    @JsonIgnore
    long getCommentsAttachableTargetId();

    void attachComments(List<CommentDto> commentsDtoList);
}
