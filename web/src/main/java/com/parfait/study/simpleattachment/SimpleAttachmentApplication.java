package com.parfait.study.simpleattachment;

import com.parfait.study.simpleattachment.board.domain.Board;
import com.parfait.study.simpleattachment.board.domain.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableFeignClients
@SpringBootApplication
public class SimpleAttachmentApplication implements CommandLineRunner {

    @Autowired
    private BoardRepository boardRepository;

    public static void main(String[] args) {
        SpringApplication.run(SimpleAttachmentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<Board> boards = IntStream.rangeClosed(1, 100)
                                      .mapToObj(n -> new Board("title" + n, "content" + n, (long) n))
                                      .collect(Collectors.toList());
        boardRepository.saveAll(boards);
    }
}

