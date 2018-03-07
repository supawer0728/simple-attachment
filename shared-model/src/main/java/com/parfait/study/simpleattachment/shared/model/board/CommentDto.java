package com.parfait.study.simpleattachment.shared.model.board;

import com.parfait.study.simpleattachment.shared.model.attachment.Attachment;
import lombok.Data;

@Data
public class CommentDto implements Attachment {
    private Long id;
    private String email;
    private String body;
}
