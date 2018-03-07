package com.parfait.study.simpleattachment.attachment.service;

import com.parfait.study.simpleattachment.shared.model.attachment.Attachable;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentType;

public interface AttachService<T extends Attachable> {
    AttachmentType getSupportAttachmentType();

    Class<T> getSupportType();

    /**
     * boolean supports(Object)를 먼저 수행 후 실행하여 형안전성을 지킬 것
     *
     * @param attachable
     * @throws ClassCastException
     */
    void attach(Attachable attachable);
}
