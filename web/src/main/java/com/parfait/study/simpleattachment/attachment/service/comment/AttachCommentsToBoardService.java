package com.parfait.study.simpleattachment.attachment.service.comment;

import com.parfait.study.simpleattachment.attachment.service.AttachService;
import com.parfait.study.simpleattachment.shared.model.attachment.Attachable;
import com.parfait.study.simpleattachment.shared.model.attachment.Attachment;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentType;
import com.parfait.study.simpleattachment.shared.model.attachment.CollectionAttachment;
import com.parfait.study.simpleattachment.shared.model.board.BoardDto;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttachCommentsToBoardService implements AttachService<BoardDto> {

    private static final Class<BoardDto> supportType = BoardDto.class;
    private final CommentClient commentClient;

    @Autowired
    public AttachCommentsToBoardService(@NonNull CommentClient commentClient) {
        this.commentClient = commentClient;
    }

    @Override
    public AttachmentType getSupportAttachmentType() {
        return AttachmentType.COMMENTS;
    }

    @Override
    public Class<BoardDto> getSupportType() {
        return supportType;
    }

    @Override
    public Attachment getAttachment(Attachable attachment) {
        BoardDto boardDto = supportType.cast(attachment);
        return new CollectionAttachment<>(commentClient.getComments(boardDto.getId()));
    }
}
