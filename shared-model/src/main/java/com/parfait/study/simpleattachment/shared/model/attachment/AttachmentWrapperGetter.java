package com.parfait.study.simpleattachment.shared.model.attachment;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface AttachmentWrapperGetter {
    @JsonIgnore
    AttachmentWrapper getAttachmentWrapper();
}
