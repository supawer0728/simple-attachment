package com.parfait.study.simpleattachment.attachment.service.writer;

import com.parfait.study.simpleattachment.attachment.service.AttachService;
import com.parfait.study.simpleattachment.shared.model.attachment.Attachable;
import com.parfait.study.simpleattachment.shared.model.attachment.Attachment;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentType;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentWrapperItem;
import com.parfait.study.simpleattachment.shared.model.board.BoardDto;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
@Component
public class AttachWriterToBoardService implements AttachService<BoardDto> {

    private static final AttachmentType supportAttachmentType = AttachmentType.WRITER;
    private static final Class<BoardDto> supportType = BoardDto.class;
    private final WriterClient writerClient;
    private final Duration timeout;

    @Autowired
    public AttachWriterToBoardService(@NonNull WriterClient writerClient,
                                      @Value("${attach.writer.timeoutMillis:5000}") long timeout) {
        this.writerClient = writerClient;
        this.timeout = Duration.ofMillis(timeout);
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
    public Mono<AttachmentWrapperItem> getAttachment(Attachable attachable) {
        return Mono.defer(() -> executeGetAttachment(attachable))
                   .subscribeOn(Schedulers.elastic())
                   .timeout(timeout)
                   .doOnError(e -> log.warn(e.getMessage(), e))
                   .onErrorReturn(AttachmentWrapperItem.ON_ERROR);
    }

    private Mono<AttachmentWrapperItem> executeGetAttachment(Attachable attachable) {
        BoardDto boardDto = supportType.cast(attachable);
        Attachment attachment = writerClient.getWriter(boardDto.getWriterId());
        return Mono.just(new AttachmentWrapperItem(supportAttachmentType, attachment));
    }
}
