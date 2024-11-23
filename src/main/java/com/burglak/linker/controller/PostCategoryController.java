package com.burglak.linker.controller;

import com.burglak.linker.dto.PostCategoryDto;
import com.burglak.linker.service.PostCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post-categories")
public class PostCategoryController {

    private final PostCategoryService postCategoryService;

    public PostCategoryController(PostCategoryService postCategoryService) {
        this.postCategoryService = postCategoryService;
    }

    @PostMapping
    public ResponseEntity<PostCategoryDto> createPostCategory(@RequestBody PostCategoryDto postCategoryDto) {
        return new ResponseEntity<>(postCategoryService.createPostCategory(postCategoryDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostCategoryDto> getPostCategories() {
        return postCategoryService.findAllPostCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostCategoryDto> getPostCategory(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postCategoryService.findPostCategoryById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public PostCategoryDto updatePostCategory(@PathVariable("id") Long id, @RequestBody PostCategoryDto postCategoryDto) {
        return postCategoryService.updateCategory(id, postCategoryDto);
    }

    @PatchMapping("/{id}")
    public PostCategoryDto partialUpdatePostCategory(@PathVariable("id") Long id, @RequestBody PostCategoryDto postCategoryDto) {
        return postCategoryService.partialUpdateCategory(id, postCategoryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostCategory(@PathVariable("id") Long id) {
        postCategoryService.deletePostCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
