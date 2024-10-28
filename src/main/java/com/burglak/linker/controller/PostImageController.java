package com.burglak.linker.controller;

import com.burglak.linker.dto.PostImageDto;
import com.burglak.linker.service.PostImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post-images")
public class PostImageController {

    private final PostImageService postImageService;

    public PostImageController(PostImageService postImageService) {
        this.postImageService = postImageService;
    }

    @PostMapping
    public ResponseEntity<PostImageDto> createPostImage(@RequestBody PostImageDto postImageDto) {
        return new ResponseEntity<>(postImageService.createPostImage(postImageDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostImageDto> getPostImages() {
        return postImageService.findAllPostImages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostImageDto> getPostImage(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postImageService.findPostImageById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public PostImageDto updatePostImage(@PathVariable("id") Long id, @RequestBody PostImageDto postImageDto) {
        return postImageService.updatePostImage(id, postImageDto);
    }

    @PatchMapping("/{id}")
    public PostImageDto partialUpdatePostImage(@PathVariable("id") Long id, @RequestBody PostImageDto postImageDto) {
        return postImageService.partialUpdatePostImage(id, postImageDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostImage(@PathVariable("id") Long id) {
        postImageService.deletePostImage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
