package com.parfiat.study.simpleattachment.webflux.attachment;

import com.parfait.study.simpleattachment.shared.model.attachment.Attachable;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentType;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentWrapperItem;
import com.parfiat.study.simpleattachment.webflux.attachment.service.AttachService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class AttachExecutor {

    private final Map<AttachmentType, List<AttachService<? extends Attachable>>> typeToServiceMap;

    @Autowired
    public AttachExecutor(@NonNull List<AttachService<? extends Attachable>> attachService) {
        this.typeToServiceMap = attachService.stream()
                                             .collect(Collectors.groupingBy(AttachService::getSupportAttachmentType, Collectors.toList()));
    }

    public Mono<Attachable> attach(Attachable attachable, AttachmentTypeHolder holder) {

        if (holder.isEmpty()) {
            return Mono.just(attachable);
        }

        return executeAttach(attachable, holder.getTypes());
    }

    private Mono<Attachable> executeAttach(Attachable attachable, Set<AttachmentType> types) {

        List<Mono<AttachmentWrapperItem>> monoItems = createMonoList(attachable, types);

        return Mono.zip(monoItems, this::filterItems)
                   .map(attachable::attach)
                   .doOnError(e -> log.warn(e.getMessage(), e))
                   .onErrorReturn(attachable);
    }

    private List<Mono<AttachmentWrapperItem>> createMonoList(Attachable attachable, Set<AttachmentType> types) {
        return types.stream()
                    .flatMap(type -> typeToServiceMap.get(type).stream())
                    .filter(service -> service.getSupportType().isAssignableFrom(attachable.getClass()))
                    .map(service -> service.getAttachment(attachable))
                    .collect(Collectors.toList());
    }

    private List<AttachmentWrapperItem> filterItems(Object[] itemArray) {
        return Stream.of(itemArray)
                     .map(AttachmentWrapperItem.class::cast)
                     .filter(item -> item != AttachmentWrapperItem.ON_ERROR)
                     .collect(Collectors.toList());
    }
}
