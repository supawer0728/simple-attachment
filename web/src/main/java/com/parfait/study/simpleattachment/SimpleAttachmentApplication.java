package com.parfait.study.simpleattachment;

import com.parfait.study.simpleattachment.board.domain.Board;
import com.parfait.study.simpleattachment.board.domain.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleAttachmentApplication implements CommandLineRunner {

    @Autowired
    private BoardRepository boardRepository;

    public static void main(String[] args) {
        SpringApplication.run(SimpleAttachmentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        boardRepository.save(new Board("title1", "content1"));
        boardRepository.save(new Board("title2", "content2"));
        boardRepository.save(new Board("title3", "content3"));
    }
}
