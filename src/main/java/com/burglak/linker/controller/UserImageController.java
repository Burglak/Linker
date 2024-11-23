package com.burglak.linker.controller;

import com.burglak.linker.dto.UserImageDto;
import com.burglak.linker.service.UserImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-images")
public class UserImageController {

    private final UserImageService userImageService;

    public UserImageController(UserImageService userImageService) {
        this.userImageService = userImageService;
    }

    @PostMapping
    public ResponseEntity<UserImageDto> createUserImage(@RequestBody UserImageDto userImageDto) {
        return new ResponseEntity<>(userImageService.createUserImage(userImageDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserImageDto> getUserImages() {
        return userImageService.findAllUserImages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserImageDto> getUserImage(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userImageService.findUserImageById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public UserImageDto updateUserImage(@PathVariable("id") Long id, @RequestBody UserImageDto userImageDto) {
        return userImageService.updateUserImage(id, userImageDto);
    }

    @PatchMapping("/{id}")
    public UserImageDto partialUpdateUserImage(@PathVariable("id") Long id, @RequestBody UserImageDto userImageDto) {
        return userImageService.partialUpdateUserImage(id, userImageDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserImage(@PathVariable("id") Long id) {
        userImageService.deleteUserImage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
