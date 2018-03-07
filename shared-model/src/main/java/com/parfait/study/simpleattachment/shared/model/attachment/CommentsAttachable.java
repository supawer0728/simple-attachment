package com.parfait.study.simpleattachment.shared.model.attachment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parfait.study.simpleattachment.shared.model.board.CommentDto;

import java.util.List;

public interface CommentsAttachable extends Attachable {
    @JsonIgnore
    long getCommentsAttachableTargetId();

    default void attachComments(List<CommentDto> commentsDtoList) {
        getAttachmentWrapper().put(AttachmentType.COMMENTS, commentsDtoList);
    }
}
