package com.parfait.study.simpleattachment.attachment.service.writer;

import com.parfait.study.simpleattachment.shared.model.board.WriterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "writer-api", url = "https://jsonplaceholder.typicode.com")
public interface WriterClient {
    @GetMapping("/users/{id}")
    WriterDto getWriterByBoardId(@PathVariable("id") long boardId);
}
