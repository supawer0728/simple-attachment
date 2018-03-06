package com.parfait.study.simpleattachment.board;

import com.parfait.study.simpleattachment.board.domain.Board;
import com.parfait.study.simpleattachment.board.domain.BoardRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardController(@NonNull BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @GetMapping
    public List<Board> getAll() {
        return boardRepository.findAll();
    }

    @GetMapping("/{id}")
    public Board getOne(@PathVariable("id") Board board) {
        return board;
    }
}
