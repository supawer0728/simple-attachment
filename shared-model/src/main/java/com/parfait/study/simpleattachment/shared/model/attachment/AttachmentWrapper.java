package com.parfait.study.simpleattachment.shared.model.attachment;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Delegate;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@ToString
@EqualsAndHashCode
public class AttachmentWrapper {

    interface AttachmentMap {
        boolean isEmpty();

        Set<Map.Entry<AttachmentType, Attachment>> entrySet();
    }

    @Delegate(types = AttachmentMap.class)
    private Map<AttachmentType, Attachment> value = new EnumMap<>(AttachmentType.class);

    public void put(AttachmentWrapperItem item) {
        this.value.put(item.getType(), item.getAttachment());
    }

    public void putAll(Collection<AttachmentWrapperItem> items) {
        this.value.putAll(items.stream().collect(Collectors.toMap(AttachmentWrapperItem::getType, AttachmentWrapperItem::getAttachment)));
    }
}
