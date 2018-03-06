package com.parfait.study.simpleattachment.config.aop.attachment.service;

import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentType;

public interface AttachService {
    AttachmentType getSupportAttachmentType();

    boolean supports(Object object);

    void attach(Object attachment);
}
