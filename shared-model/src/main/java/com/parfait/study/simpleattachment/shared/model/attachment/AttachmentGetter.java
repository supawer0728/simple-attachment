package com.parfait.study.simpleattachment.shared.model.attachment;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.Map;

public interface AttachmentGetter extends AttachmentWrapperGetter {
    @JsonAnyGetter
    default Map<String, Object> getAttachment() {
        return getAttachmentWrapper().getAttachment();
    }
}
