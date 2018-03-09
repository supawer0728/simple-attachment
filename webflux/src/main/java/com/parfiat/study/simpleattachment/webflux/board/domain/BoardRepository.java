package com.parfiat.study.simpleattachment.webflux.board.domain;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BoardRepository {

    private long generatedId = 0L;
    private Map<Long, Board> boardMap = new ConcurrentHashMap<>();

    public void saveAll(List<Board> boards) {
        boards.forEach(board -> {
            board.setId(++generatedId);
            boardMap.put(board.getId(), board);
        });
    }

    public Mono<Board> findOne(Long id) {
        return Mono.just(boardMap.get(id));
    }
}
