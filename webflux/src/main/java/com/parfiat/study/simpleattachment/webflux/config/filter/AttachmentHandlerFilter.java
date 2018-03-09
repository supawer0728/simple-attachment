package com.parfiat.study.simpleattachment.webflux.config.filter;

import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentType;
import com.parfiat.study.simpleattachment.webflux.attachment.AttachmentTypeHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AttachmentHandlerFilter {

    public static final String TARGET_ATTRIBUTE_NAME = "attachment";
    private static final String TARGET_QUERY_PARAM_NAME = "attachment";
    private static final String TARGET_DELIMITER = ",";

    public HandlerFilterFunction<ServerResponse, ServerResponse> filter() {
        return (request, next) -> {
            Set<AttachmentType> attachmentTypes = resolveAttachmentType(request);
            request.attributes().put(TARGET_ATTRIBUTE_NAME, new AttachmentTypeHolder(attachmentTypes));
            return next.handle(request);
        };
    }

    private Set<AttachmentType> resolveAttachmentType(ServerRequest request) {
        return request.queryParam(TARGET_QUERY_PARAM_NAME)
                      .map(attachments -> Stream.of(attachments.split(TARGET_DELIMITER))
                                                .map(AttachmentType::fromCaseIgnoredName)
                                                .filter(Objects::nonNull)
                                                .collect(Collectors.toSet()))
                      .orElse(Collections.emptySet());
    }
}
