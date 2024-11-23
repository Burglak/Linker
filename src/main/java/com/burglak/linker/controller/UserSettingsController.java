package com.burglak.linker.controller;

import com.burglak.linker.dto.UserSettingsDto;
import com.burglak.linker.service.UserSettingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-settings")
public class UserSettingsController {

    private final UserSettingsService userSettingsService;

    public UserSettingsController(UserSettingsService userSettingsService) {
        this.userSettingsService = userSettingsService;
    }

    @PostMapping
    public ResponseEntity<UserSettingsDto> createUserSettings(@RequestBody UserSettingsDto userSettingsDto) {
        return new ResponseEntity<>(userSettingsService.createUserSettings(userSettingsDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserSettingsDto> getUserSettings() {
        return userSettingsService.findAllUserSettings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSettingsDto> getUserSetting(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userSettingsService.findUserSettingsById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public UserSettingsDto updateUserSettings(@PathVariable("id") Long id, @RequestBody UserSettingsDto userSettingsDto) {
        return userSettingsService.updateUserSettings(id, userSettingsDto);
    }

    @PatchMapping("/{id}")
    public UserSettingsDto partialUpdateUserSettings(@PathVariable("id") Long id, @RequestBody UserSettingsDto userSettingsDto) {
        return userSettingsService.partialUpdateUserSettings(id, userSettingsDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserSettings(@PathVariable("id") Long id) {
        userSettingsService.deleteUserSettings(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
