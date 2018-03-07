package com.parfait.study.simpleattachment.shared.model.attachment;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Value;
import lombok.experimental.Delegate;

import java.util.Collection;

@Value
public class CollectionAttachment<T> implements Attachment, Collection<T> {

    @Delegate
    private Collection<T> value;

    @JsonUnwrapped
    public Collection<T> getValue() {
        return value;
    }
}
