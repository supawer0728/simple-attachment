package com.parfait.study.simpleattachment.shared.model.attachment;

import lombok.Value;

@Value
public class AttachmentWrapperItem {
    private AttachmentType type;
    private Attachment attachment;
}
