package com.burglak.linker.controller;

import com.burglak.linker.dto.UserStatusDto;
import com.burglak.linker.service.UserStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-statuses")
public class UserStatusController {

    private final UserStatusService userStatusService;

    public UserStatusController(UserStatusService userStatusService) {
        this.userStatusService = userStatusService;
    }

    @PostMapping
    public ResponseEntity<UserStatusDto> createUserStatus(@RequestBody UserStatusDto userStatusDto) {
        return new ResponseEntity<>(userStatusService.createUserStatus(userStatusDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserStatusDto> getUserStatuses() {
        return userStatusService.findAllUserStatuses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserStatusDto> getUserStatus(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userStatusService.findUserStatusById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public UserStatusDto updateUserStatus(@PathVariable("id") Long id, @RequestBody UserStatusDto userStatusDto) {
        return userStatusService.updateUserStatus(id, userStatusDto);
    }

    @PatchMapping("/{id}")
    public UserStatusDto partialUpdateUserStatus(@PathVariable("id") Long id, @RequestBody UserStatusDto userStatusDto) {
        return userStatusService.partialUpdateUserStatus(id, userStatusDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserStatus(@PathVariable("id") Long id) {
        userStatusService.deleteUserStatus(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
