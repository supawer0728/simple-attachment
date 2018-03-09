package com.parfiat.study.simpleattachment.webflux.attachment.service.writer;

import com.parfait.study.simpleattachment.shared.model.board.WriterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "writer-api", url = "https://jsonplaceholder.typicode.com")
public interface WriterClient {
    @GetMapping("/users/{id}")
    WriterDto getWriter(@PathVariable("id") long id);
}
