package com.parfait.study.simpleattachment.shared.model.attachment;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public interface Attachable {
    AttachmentWrapper getAttachmentWrapper();

    default Attachable attach(Collection<AttachmentWrapperItem> items) {
        getAttachmentWrapper().putAll(items);
        return this;
    }

    @JsonAnyGetter
    default Map<String, Object> getAttachment() {
        AttachmentWrapper wrapper = getAttachmentWrapper();

        if (wrapper.isEmpty()) {
            return null;
        }

        return wrapper.entrySet()
                      .stream()
                      .collect(Collectors.toMap(e -> e.getKey().lowerCaseName(), Map.Entry::getValue));
    }
}
