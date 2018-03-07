package com.parfait.study.simpleattachment.attachment.service.writer;

import com.parfait.study.simpleattachment.attachment.service.AttachService;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentType;
import com.parfait.study.simpleattachment.shared.model.attachment.WriterAttachable;
import com.parfait.study.simpleattachment.shared.model.board.WriterDto;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class WriterAttachService implements AttachService<WriterAttachable> {

    private final WriterClient writerClient;
    private static final Class<WriterAttachable> supportType = WriterAttachable.class;

    public WriterAttachService(@NonNull WriterClient writerClient) {
        this.writerClient = writerClient;
    }

    @Override
    public AttachmentType getSupportAttachmentType() {
        return AttachmentType.WRITER;
    }

    @Override
    public Class<WriterAttachable> getSupportType() {
        return supportType;
    }

    @Override
    public void attach(Object attachment) {
        WriterAttachable writerAttachable = supportType.cast(attachment);
        WriterDto writerDto = writerClient.getWriterByBoardId(writerAttachable.getWriterAttachableTargetId());
        writerAttachable.attachWriter(writerDto);
    }
}
