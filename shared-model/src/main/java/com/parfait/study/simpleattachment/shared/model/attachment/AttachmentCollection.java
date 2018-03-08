package com.parfait.study.simpleattachment.shared.model.attachment;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Collection;

public interface AttachmentCollection<T extends Attachment> extends Attachment, Collection<T> {

    @JsonUnwrapped
    Collection<T> getValue();
}
