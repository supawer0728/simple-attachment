package com.parfait.study.simpleattachment.board.converter;

import com.parfait.study.simpleattachment.board.domain.Board;
import com.parfait.study.simpleattachment.shared.model.board.BoardDto;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BoardDtoConverter implements Converter<Board, BoardDto> {

    @Override
    public BoardDto convert(@NonNull Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setId(board.getId());
        boardDto.setTitle(board.getTitle());
        boardDto.setContent(board.getContent());
        return boardDto;
    }
}
