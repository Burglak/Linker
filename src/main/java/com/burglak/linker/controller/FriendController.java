package com.burglak.linker.controller;

import com.burglak.linker.dto.FriendDto;
import com.burglak.linker.service.FriendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/friends")
public class FriendController {

    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping
    public ResponseEntity<FriendDto> createFriend(@RequestBody FriendDto friendDto) {
        return new ResponseEntity<>(friendService.createFriend(friendDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<FriendDto> getFriends() {
        return friendService.findAllFriends();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FriendDto> getFriend(@PathVariable("id") Long id) {
        return new ResponseEntity<>(friendService.findFriendById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public FriendDto updateFriend(@PathVariable("id") Long id, @RequestBody FriendDto friendDto) {
        return friendService.updateFriend(id, friendDto);
    }

    @PatchMapping("/{id}")
    public FriendDto partialUpdateFriend(@PathVariable("id") Long id, @RequestBody FriendDto friendDto) {
        return friendService.partialUpdateFriend(id, friendDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable("id") Long id) {
        friendService.deleteFriend(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
