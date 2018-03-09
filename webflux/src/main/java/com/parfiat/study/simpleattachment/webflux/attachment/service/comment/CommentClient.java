package com.parfiat.study.simpleattachment.webflux.attachment.service.comment;

import com.parfait.study.simpleattachment.shared.model.board.CommentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "comment-api", url = "https://jsonplaceholder.typicode.com")
public interface CommentClient {
    @GetMapping("/comments")
    List<CommentDto> getComments(@RequestParam("postId") Long boardId);
}
