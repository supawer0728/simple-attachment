package com.parfait.study.simpleattachment.shared.model.attachment;

import lombok.Value;

@Value
public class AttachmentWrapperItem {
    public static final AttachmentWrapperItem ON_ERROR = new AttachmentWrapperItem(null, null);
    private AttachmentType type;
    private Attachment attachment;
}
