package com.parfait.study.simpleattachment.shared.model.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.parfait.study.simpleattachment.shared.model.attachment.Attachable;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentWrapper;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardDto implements Attachable {
    private Long id;
    private String title;
    private String content;
    @JsonIgnore
    private Long writerId;

    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    private AttachmentWrapper attachmentWrapper = new AttachmentWrapper();
}
