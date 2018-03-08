package com.parfait.study.simpleattachment.board;

import com.parfait.study.simpleattachment.attachment.Attach;
import com.parfait.study.simpleattachment.board.converter.BoardDtoConverter;
import com.parfait.study.simpleattachment.board.domain.Board;
import com.parfait.study.simpleattachment.board.domain.BoardRepository;
import com.parfait.study.simpleattachment.shared.model.board.BoardDto;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardDtoConverter boardDtoConverter;

    @Autowired
    public BoardController(@NonNull BoardRepository boardRepository,
                           @NonNull BoardDtoConverter boardDtoConverter) {
        this.boardRepository = boardRepository;
        this.boardDtoConverter = boardDtoConverter;
    }

    @GetMapping
    public List<BoardDto> getAll() {
        return boardRepository.findAll()
                              .stream()
                              .map(boardDtoConverter::convert)
                              .collect(Collectors.toList());
    }

    @Attach
    @GetMapping("/{id}")
    public BoardDto getOne(@PathVariable("id") Board board) {
        return boardDtoConverter.convert(board);
    }
}
