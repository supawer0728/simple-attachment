package com.parfait.study.simpleattachment.shared.model.attachment;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AttachmentType {
    COMMENTS("comments");

    private static Map<String, AttachmentType> queryToTypeMap;
    private final String query;

    AttachmentType(String query) {
        this.query = query;
    }

    public static AttachmentType fromQuery(String query) {

        if (queryToTypeMap == null) {
            initQueryToTypeMap();
        }

        return queryToTypeMap.get(query);
    }

    private static void initQueryToTypeMap() {
        Map<String, AttachmentType> temp = Stream.of(AttachmentType.values())
                                                 .collect(Collectors.toMap(type -> type.query, Function.identity()));

        AttachmentType.queryToTypeMap = Collections.unmodifiableMap(temp);
    }
}
