package com.parfait.study.simpleattachment.attachment.service;

import com.parfait.study.simpleattachment.shared.model.attachment.Attachable;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentType;

public interface AttachService<T extends Attachable> {
    AttachmentType getSupportAttachmentType();

    Class<T> getSupportType();

    void attach(Object attachment);
}
