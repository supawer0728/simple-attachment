package com.parfait.study.simpleattachment.shared.model.attachment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parfait.study.simpleattachment.shared.model.board.WriterDto;

public interface WriterAttachable extends Attachable {
    @JsonIgnore
    long getWriterAttachableTargetId();

    default void attachWriter(WriterDto writerDto) {
        getAttachmentWrapper().put(AttachmentType.WRITER, writerDto);
    }
}
