package com.burglak.linker.controller;

import com.burglak.linker.dto.PostCommentDto;
import com.burglak.linker.service.PostCommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/postComments")
public class PostCommentController {

    private final PostCommentService postCommentService;

    public PostCommentController(PostCommentService postCommentService) {
        this.postCommentService = postCommentService;
    }

    @PostMapping
    public ResponseEntity<PostCommentDto> createPostComment(@RequestBody PostCommentDto postCommentDto) {
        return new ResponseEntity<>(postCommentService.createPostComment(postCommentDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostCommentDto> getPostComments() {
        return postCommentService.findAllPostComments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostCommentDto> getPostComment(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postCommentService.findPostCommentById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public PostCommentDto updatePostComment(@PathVariable("id") Long id, @RequestBody PostCommentDto postCommentDto) {
        return postCommentService.updatePostComment(id, postCommentDto);
    }

    @PatchMapping("/{id}")
    public PostCommentDto partialUpdatePostComment(@PathVariable("id") Long id, @RequestBody PostCommentDto postCommentDto) {
        return postCommentService.partialUpdatePostComment(id, postCommentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostComment(@PathVariable("id") Long id) {
        postCommentService.deletePostComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

