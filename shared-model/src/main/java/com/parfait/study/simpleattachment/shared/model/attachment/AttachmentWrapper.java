package com.parfait.study.simpleattachment.shared.model.attachment;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Delegate;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

@ToString
@EqualsAndHashCode
public class AttachmentWrapper {

    interface AttachmentMap {
        void put(AttachmentType type, Attachment attachment);

        void putAll(Map<? extends AttachmentType, ? extends Attachment> attachmentMap);

        boolean isEmpty();

        Set<Map.Entry<AttachmentType, Attachment>> entrySet();
    }

    @Delegate(types = AttachmentMap.class)
    private Map<AttachmentType, Attachment> value = new EnumMap<>(AttachmentType.class);
}
