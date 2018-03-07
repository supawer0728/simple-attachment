package com.parfait.study.simpleattachment.config.aop.attachment.service.comment;

import com.parfait.study.simpleattachment.config.aop.attachment.service.AttachService;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentType;
import com.parfait.study.simpleattachment.shared.model.attachment.CommentsAttachable;
import com.parfait.study.simpleattachment.shared.model.board.CommentDto;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentsAttachService implements AttachService<CommentsAttachable> {

    private final CommentClient commentClient;
    private static final Class<CommentsAttachable> supportType = CommentsAttachable.class;

    public CommentsAttachService(@NonNull CommentClient commentClient) {
        this.commentClient = commentClient;
    }

    @Override
    public AttachmentType getSupportAttachmentType() {
        return AttachmentType.COMMENTS;
    }

    @Override
    public Class<CommentsAttachable> getSupportType() {
        return supportType;
    }

    /**
     * boolean supports(Object)를 먼저 수행 후 실행하여 형안전성을 지킬 것
     *
     * @param attachment
     * @throws ClassCastException
     */
    @Override
    public void attach(Object attachment) {
        CommentsAttachable commentsAttachable = supportType.cast(attachment);
        List<CommentDto> comments = commentClient.getComments(commentsAttachable.getCommentsAttachableTargetId());
        commentsAttachable.attachComments(comments);
    }
}
