package com.parfait.study.simpleattachment.config.interceptor.attachment;

import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentType;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Set;

@RequestScope
@Component
@Data
public class AttachmentTypeHolder {
    private Set<AttachmentType> types;
}
