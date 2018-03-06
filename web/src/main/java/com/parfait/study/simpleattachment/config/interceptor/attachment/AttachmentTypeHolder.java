package com.parfait.study.simpleattachment.config.interceptor.attachment;

import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentType;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Collections;
import java.util.Set;

@RequestScope
@Component
public class AttachmentTypeHolder {
    private Set<AttachmentType> types;

    public void setTypes(@NonNull Set<AttachmentType> types) {
        this.types = Collections.unmodifiableSet(types);
    }

    public boolean isEmpty() {
        return CollectionUtils.isEmpty(types);
    }

    public Set<AttachmentType> getTypes() {
        return types;
    }
}
