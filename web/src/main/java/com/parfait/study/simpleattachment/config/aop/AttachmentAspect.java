package com.parfait.study.simpleattachment.config.aop;

import com.parfait.study.simpleattachment.attachment.AttachmentTypeHolder;
import com.parfait.study.simpleattachment.attachment.service.AttachService;
import com.parfait.study.simpleattachment.shared.model.attachment.Attachable;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentType;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentWrapperItem;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
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

    @Pointcut("@annotation(com.parfait.study.simpleattachment.attachment.Attach)")
    private void pointcut() {
    }

    @AfterReturning(pointcut = "pointcut()", returning = "returnValue")
    public Object afterReturning(Object returnValue) {

        if (attachmentTypeHolder.isEmpty() && !(returnValue instanceof Attachable)) {
            return returnValue;
        }

        executeAttach((Attachable) returnValue);

        return returnValue;
    }

    private void executeAttach(Attachable attachable) {

        List<Mono<AttachmentWrapperItem>> monoItems = createMonoList(attachable);

        List<AttachmentWrapperItem> items = executeMonoAndCollectList(monoItems);

        attachable.attach(items);
    }

    private List<Mono<AttachmentWrapperItem>> createMonoList(Attachable attachable) {
        Set<AttachmentType> types = attachmentTypeHolder.getTypes();
        return types.stream()
                    .flatMap(type -> typeToServiceMap.get(type).stream())
                    .filter(service -> service.getSupportType().isAssignableFrom(attachable.getClass()))
                    .map(service -> service.getAttachment(attachable))
                    .collect(Collectors.toList());
    }

    private List<AttachmentWrapperItem> executeMonoAndCollectList(List<Mono<AttachmentWrapperItem>> monoItems) {
        return Mono.zip(monoItems, this::filterItems)
                   .doOnError(e -> log.warn(e.getMessage(), e))
                   .onErrorReturn(Collections.emptyList())
                   .block();
    }

    private List<AttachmentWrapperItem> filterItems(Object[] itemArray) {
        return Stream.of(itemArray)
                     .map(AttachmentWrapperItem.class::cast)
                     .filter(item -> item != AttachmentWrapperItem.ON_ERROR)
                     .collect(Collectors.toList());
    }
}
