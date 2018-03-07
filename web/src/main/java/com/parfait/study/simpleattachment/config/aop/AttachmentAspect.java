package com.parfait.study.simpleattachment.config.aop;

import com.parfait.study.simpleattachment.attachment.AttachmentTypeHolder;
import com.parfait.study.simpleattachment.attachment.service.AttachService;
import com.parfait.study.simpleattachment.shared.model.attachment.Attachable;
import com.parfait.study.simpleattachment.shared.model.attachment.Attachment;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentType;
import lombok.NonNull;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Aspect
public class AttachmentAspect {

    private final AttachmentTypeHolder attachmentTypeHolder;
    private final Map<AttachmentType, List<AttachService<? extends Attachable>>> typeToServiceMap;

    @Autowired
    public AttachmentAspect(@NonNull AttachmentTypeHolder attachmentTypeHolder,
                            @NonNull List<AttachService<? extends Attachable>> attachService) {
        this.attachmentTypeHolder = attachmentTypeHolder;
        this.typeToServiceMap = attachService.stream()
                                             .collect(Collectors.groupingBy(AttachService::getSupportAttachmentType, Collectors.toList()));
    }

    @Pointcut("@annotation(com.parfait.study.simpleattachment.attachment.Attachable)")
    private void pointcut() {
    }

    @AfterReturning(pointcut = "pointcut()", returning = "returnValue")
    public Object afterReturning(Object returnValue) {

        if (attachmentTypeHolder.isEmpty() && !(returnValue instanceof Attachable)) {
            return returnValue;
        }

        executeAttach(returnValue);

        return returnValue;
    }

    private void executeAttach(Object returnValue) {
        Attachable attachable = (Attachable) returnValue;

        Set<AttachmentType> types = attachmentTypeHolder.getTypes();

        Map<AttachmentType, Attachment> attachmentMap =
                types.stream()
                     .flatMap(type -> typeToServiceMap.get(type).stream())
                     .filter(service -> service.getSupportType().isAssignableFrom(returnValue.getClass()))
                     .collect(Collectors.toMap(AttachService::getSupportAttachmentType, s -> s.getAttachment(attachable)));

        attachable.setAttachmentMap(attachmentMap);
    }
}
