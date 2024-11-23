package com.burglak.linker.controller;

import com.burglak.linker.dto.UserSavedPostDto;
import com.burglak.linker.service.UserSavedPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-saved-posts")
public class UserSavedPostController {

    private final UserSavedPostService userSavedPostService;

    public UserSavedPostController(UserSavedPostService userSavedPostService) {
        this.userSavedPostService = userSavedPostService;
    }

    @PostMapping
    public ResponseEntity<UserSavedPostDto> createUserSavedPost(@RequestBody UserSavedPostDto userSavedPostDto) {
        return new ResponseEntity<>(userSavedPostService.createUserSavedPost(userSavedPostDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserSavedPostDto> getUserSavedPosts() {
        return userSavedPostService.findAllUserSavedPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSavedPostDto> getUserSavedPost(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userSavedPostService.findUserSavedPostById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public UserSavedPostDto updateUserSavedPost(@PathVariable("id") Long id, @RequestBody UserSavedPostDto userSavedPostDto) {
        return userSavedPostService.updateUserSavedPost(id, userSavedPostDto);
    }

    @PatchMapping("/{id}")
    public UserSavedPostDto partialUpdateUserSavedPost(@PathVariable("id") Long id, @RequestBody UserSavedPostDto userSavedPostDto) {
        return userSavedPostService.partialUpdateUserSavedPost(id, userSavedPostDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserSavedPost(@PathVariable("id") Long id) {
        userSavedPostService.deleteUserSavedPost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
