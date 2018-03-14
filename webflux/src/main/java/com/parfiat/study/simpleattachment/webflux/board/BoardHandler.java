package com.parfiat.study.simpleattachment.webflux.board;

import com.parfait.study.simpleattachment.shared.model.attachment.Attachable;
import com.parfiat.study.simpleattachment.webflux.attachment.AttachExecutor;
import com.parfiat.study.simpleattachment.webflux.attachment.AttachmentTypeHolder;
import com.parfiat.study.simpleattachment.webflux.board.converter.BoardDtoConverter;
import com.parfiat.study.simpleattachment.webflux.board.domain.BoardRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static com.parfiat.study.simpleattachment.webflux.config.filter.AttachmentHandlerFilter.TARGET_ATTRIBUTE_NAME;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class BoardHandler {
    private final BoardRepository boardRepository;
    private final BoardDtoConverter boardDtoConverter;
    private final AttachExecutor attachExecutor;
    private final Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    @Autowired
    public BoardHandler(@NonNull BoardRepository boardRepository,
                        @NonNull BoardDtoConverter boardDtoConverter,
                        @NonNull AttachExecutor attachExecutor) {
        this.boardRepository = boardRepository;
        this.boardDtoConverter = boardDtoConverter;
        this.attachExecutor = attachExecutor;
    }

    public Mono<ServerResponse> getBoard(ServerRequest request) {
        long boardId = Long.valueOf(request.pathVariable("id"));
        AttachmentTypeHolder typeHolder = request.attribute(TARGET_ATTRIBUTE_NAME)
                                                 .map(AttachmentTypeHolder.class::cast)
                                                 .orElseGet(() -> new AttachmentTypeHolder(Collections.emptySet()));
        Mono<Attachable> attachableMono =
                boardRepository.findOne(boardId)
                               .map(boardDtoConverter::convert)
                               .flatMap(boardDto -> attachExecutor.attach(boardDto, typeHolder));

        return attachableMono.flatMap(boardDto -> ServerResponse.ok()
                                                                .contentType(MediaType.APPLICATION_JSON)
                                                                .body(fromObject(boardDto))
                                                                .switchIfEmpty(notFound));
    }
}
