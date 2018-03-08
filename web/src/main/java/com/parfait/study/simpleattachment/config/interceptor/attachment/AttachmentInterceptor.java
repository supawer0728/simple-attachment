package com.parfait.study.simpleattachment.config.interceptor.attachment;

import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentType;
import lombok.NonNull;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AttachmentInterceptor extends HandlerInterceptorAdapter {

    public static final String TARGET_PARAMETER_NAME = "attachment";
    private final Map<HandlerMethod, Boolean> attachableMap = new ConcurrentHashMap<>();
    private final AttachmentTypeHolder attachmentTypeHolder;

    @Autowired
    public AttachmentInterceptor(@NonNull AttachmentTypeHolder attachmentTypeHolder) {
        this.attachmentTypeHolder = attachmentTypeHolder;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        boolean isAttachable = attachableMap.computeIfAbsent(handlerMethod, key -> key.hasMethodAnnotation(Attach.class));
        if (!isAttachable) {
            return true;
        }

        Set<AttachmentType> types = resolveAttachmentType(request);
        attachmentTypeHolder.setTypes(types);

        return true;
    }

    private Set<AttachmentType> resolveAttachmentType(HttpServletRequest request) {
        String attachments = request.getParameter(TARGET_PARAMETER_NAME);

        if (StringUtils.isBlank(attachments)) {
            return Collections.emptySet();
        }

        return Stream.of(attachments.split(","))
                     .map(AttachmentType::fromQuery)
                     .collect(Collectors.toSet());
    }
}
