package com.parfait.study.simpleattachment.shared.model.attachment;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.Map;
import java.util.stream.Collectors;

public interface Attachable {
    Map<AttachmentType, Attachment> getAttachmentMap();

    default void attach(AttachmentType type, Attachment attachment) {
        getAttachmentMap().put(type, attachment);
    }

    default void attach(Map<? extends AttachmentType, ? extends Attachment> attachment) {
        getAttachmentMap().putAll(attachment);
    }

    @JsonAnyGetter
    default Map<String, Object> getAttachment() {
        Map<AttachmentType, Attachment> attachmentMap = getAttachmentMap();

        if (attachmentMap.isEmpty()) {
            return null;
        }

        return attachmentMap.entrySet()
                            .stream()
                            .collect(Collectors.toMap(e -> e.getKey().name().toLowerCase(), Map.Entry::getValue));
    }
}
