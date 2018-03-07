package com.parfait.study.simpleattachment.shared.model.attachment;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.Map;
import java.util.stream.Collectors;

public interface Attachable {
    Map<AttachmentType, Attachment> getAttachmentMap();

    void setAttachmentMap(Map<AttachmentType, Attachment> attachmentMap);

    @JsonAnyGetter
    default Map<String, Object> getAttachment() {
        return getAttachmentMap().entrySet()
                                 .stream()
                                 .collect(Collectors.toMap(e -> e.getKey().name().toLowerCase(), Map.Entry::getValue));
    }
}
