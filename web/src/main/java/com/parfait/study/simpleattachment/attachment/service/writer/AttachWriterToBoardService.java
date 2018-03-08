package com.parfait.study.simpleattachment.attachment.service.writer;

import com.parfait.study.simpleattachment.attachment.service.AttachService;
import com.parfait.study.simpleattachment.shared.model.attachment.Attachable;
import com.parfait.study.simpleattachment.shared.model.attachment.Attachment;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentType;
import com.parfait.study.simpleattachment.shared.model.board.BoardDto;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttachWriterToBoardService implements AttachService<BoardDto> {

    private static final AttachmentType supportAttachmentType = AttachmentType.WRITER;
    private static final Class<BoardDto> supportType = BoardDto.class;
    private final WriterClient writerClient;

    @Autowired
    public AttachWriterToBoardService(@NonNull WriterClient writerClient) {
        this.writerClient = writerClient;
    }

    @Override
    public AttachmentType getSupportAttachmentType() {
        return supportAttachmentType;
    }

    @Override
    public Class<BoardDto> getSupportType() {
        return supportType;
    }

    @Override
    public Attachment getAttachment(Attachable attachment) {
        BoardDto boardDto = supportType.cast(attachment);
        return writerClient.getWriter(boardDto.getWriterId());
    }
}
