package com.parfait.study.simpleattachment.shared.model.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentWrapper;
import com.parfait.study.simpleattachment.shared.model.attachment.CommentsAttachable;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardDto implements CommentsAttachable {
    private Long id;
    private String title;
    private String content;

    @JsonIgnore
    private AttachmentWrapper attachmentWrapper = new AttachmentWrapper();

    @Override
    public long getCommentsAttachableTargetId() {
        return id;
    }
}
