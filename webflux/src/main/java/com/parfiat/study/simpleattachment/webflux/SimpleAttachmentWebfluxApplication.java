package com.parfiat.study.simpleattachment.webflux;

import com.parfiat.study.simpleattachment.webflux.board.domain.Board;
import com.parfiat.study.simpleattachment.webflux.board.domain.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@EnableFeignClients
@SpringBootApplication
public class SimpleAttachmentWebfluxApplication implements CommandLineRunner {

    @Autowired
    private BoardRepository boardRepository;

    public static void main(String[] args) {
        SpringApplication.run(SimpleAttachmentWebfluxApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<Board> boards = IntStream.rangeClosed(1, 100)
                                      .mapToObj(n -> new Board("title" + n, "content" + n, (long) n))
                                      .collect(Collectors.toList());
        boardRepository.saveAll(boards);
    }
}

