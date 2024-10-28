package com.burglak.linker.controller;

import com.burglak.linker.dto.FollowDto;
import com.burglak.linker.service.FollowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FollowController {

    private FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping(path = "/api/v1/follows")
    public ResponseEntity<FollowDto> createFollow(@RequestBody FollowDto followDto) {
        return new ResponseEntity<>(followService.createFollow(followDto), HttpStatus.CREATED); // Status: 201
    }

    @GetMapping(path = "/api/v1/follows")
    public List<FollowDto> getFollows() {
        return followService.findAllFollows(); // Status: 200
    }

    @GetMapping(path = "/api/v1/follows/{id}")
    public ResponseEntity<FollowDto> getFollow(@PathVariable("id") Long id) {
        return new ResponseEntity<>(followService.findFollowById(id), HttpStatus.OK); // Status: 200 or 404 if resource does not exist
    }

    @PutMapping(path = "/api/v1/follows/{id}")
    public FollowDto updateFollow(@PathVariable("id") Long id, @RequestBody FollowDto followDto) {
        return followService.updateFollow(id, followDto); // Status: 200 or 404 if resource does not exist
    }

    @PatchMapping(path = "/api/v1/follows/{id}")
    public FollowDto partialUpdateFollow(@PathVariable("id") Long id, @RequestBody FollowDto followDto) {
        return followService.partialUpdateFollow(id, followDto); // Status: 200 or 404 if resource does not exist
    }

    @DeleteMapping(path = "/api/v1/follows/{id}")
    public ResponseEntity<FollowDto> deleteFollow(@PathVariable("id") Long id) {
        followService.deleteFollow(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Status: 204
    }
}
