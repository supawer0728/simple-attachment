package com.parfait.study.simpleattachment.shared.model.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentWrapper;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardDto {
    private Long id;
    private String title;
    private String content;

    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    private AttachmentWrapper attachmentWrapper = new AttachmentWrapper();
}
