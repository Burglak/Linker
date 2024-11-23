package com.burglak.linker.controller;

import com.burglak.linker.dto.PostReactionDto;
import com.burglak.linker.service.PostReactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post-reactions")
public class PostReactionController {

    private final PostReactionService postReactionService;

    public PostReactionController(PostReactionService postReactionService) {
        this.postReactionService = postReactionService;
    }

    @PostMapping
    public ResponseEntity<PostReactionDto> createPostReaction(@RequestBody PostReactionDto postReactionDto) {
        return new ResponseEntity<>(postReactionService.createPostReaction(postReactionDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostReactionDto> getPostReactions() {
        return postReactionService.findAllPostReactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostReactionDto> getPostReaction(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postReactionService.findPostReactionById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public PostReactionDto updatePostReaction(@PathVariable("id") Long id, @RequestBody PostReactionDto postReactionDto) {
        return postReactionService.updatePostReaction(id, postReactionDto);
    }

    @PatchMapping("/{id}")
    public PostReactionDto partialUpdatePostReaction(@PathVariable("id") Long id, @RequestBody PostReactionDto postReactionDto) {
        return postReactionService.partialUpdatePostReaction(id, postReactionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostReaction(@PathVariable("id") Long id) {
        postReactionService.deletePostReaction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
