package com.parfait.study.simpleattachment.shared.model.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.parfait.study.simpleattachment.shared.model.attachment.Attachable;
import com.parfait.study.simpleattachment.shared.model.attachment.Attachment;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.EnumMap;
import java.util.Map;

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
    private Map<AttachmentType, Attachment> attachmentMap = new EnumMap<>(AttachmentType.class);
}
