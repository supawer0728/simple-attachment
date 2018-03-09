package com.parfiat.study.simpleattachment.webflux.attachment.service;

import com.parfait.study.simpleattachment.shared.model.attachment.Attachable;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentType;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentWrapperItem;
import reactor.core.publisher.Mono;

public interface AttachService<T extends Attachable> {
    AttachmentType getSupportAttachmentType();

    Class<T> getSupportType();

    /**
     * boolean supports(Object)를 먼저 수행 후 실행하여 형안전성을 지킬 것
     *
     * @param attachable
     * @throws ClassCastException
     */
    Mono<AttachmentWrapperItem> getAttachment(Attachable attachable);
}
