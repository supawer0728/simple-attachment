package com.parfait.study.simpleattachment.shared.model.attachment;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

@ToString
@EqualsAndHashCode
public class AttachmentWrapper {

    private final Map<AttachmentType, Object> internalAttachments = new EnumMap<>(AttachmentType.class);

    public void put(AttachmentType type, Object attachment) {
        internalAttachments.put(type, attachment);
    }

    public Map<String, Object> getAttachment() {
        return internalAttachments.entrySet()
                                  .stream()
                                  .collect(Collectors.toMap(entry -> entry.getKey().name().toLowerCase(), Map.Entry::getValue));
    }
}
