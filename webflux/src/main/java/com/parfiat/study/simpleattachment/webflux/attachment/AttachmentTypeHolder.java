package com.parfiat.study.simpleattachment.webflux.attachment;

import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentType;
import lombok.NonNull;
import lombok.Value;

import java.util.Collections;
import java.util.Set;

@Value
public class AttachmentTypeHolder {
    private Set<AttachmentType> types;

    public AttachmentTypeHolder(@NonNull Set<AttachmentType> types) {
        this.types = Collections.unmodifiableSet(types);
    }

    public boolean isEmpty() {
        return types.isEmpty();
    }

    public Set<AttachmentType> getTypes() {
        return types;
    }
}
