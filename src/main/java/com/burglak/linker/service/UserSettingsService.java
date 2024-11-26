package com.burglak.linker.service;

import com.burglak.linker.dto.UserSettingsDto;
import com.burglak.linker.exception.UserSettingsNotFoundException;
import com.burglak.linker.mapper.impl.UserSettingsMapper;
import com.burglak.linker.model.entity.UserSettings;
import com.burglak.linker.repository.UserSettingsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UserSettingsService {

    private UserSettingsRepository userSettingsRepository;

    private UserSettingsMapper userSettingsMapper;

    public UserSettingsService(UserSettingsRepository userSettingsRepository, UserSettingsMapper userSettingsMapper) {
        this.userSettingsRepository = userSettingsRepository;
        this.userSettingsMapper = userSettingsMapper;
    }

    public UserSettingsDto createUserSettings(UserSettingsDto userSettingsDto) {
        UserSettings userSettings = userSettingsMapper.mapFrom(userSettingsDto);
        UserSettings createdUserSettings = userSettingsRepository.save(userSettings);
        return userSettingsMapper.mapTo(createdUserSettings);
    }

    public UserSettingsDto findUserSettingsById(Long id) {
        UserSettings userSettings = userSettingsRepository.findById(id).orElseThrow(() -> new UserSettingsNotFoundException(id));
        return userSettingsMapper.mapTo(userSettings);
    }

    public List<UserSettingsDto> findAllUserSettings() {
        List<UserSettings> userSettingsList = StreamSupport.stream(userSettingsRepository.findAll().spliterator(), false).toList();
        return userSettingsList.stream().map(userSettingsMapper::mapTo).toList();
    }

    public UserSettingsDto updateUserSettings(Long id, UserSettingsDto userSettingsDto) {
        userSettingsDto.setId(id);
        UserSettings existingUserSettings = userSettingsRepository.findById(id).orElseThrow(() -> new UserSettingsNotFoundException(id));
        existingUserSettings = userSettingsMapper.mapFrom(userSettingsDto);
        UserSettings savedUserSettings = userSettingsRepository.save(existingUserSettings);
        return userSettingsMapper.mapTo(savedUserSettings);
    }

    //todo: update partial update
    public UserSettingsDto partialUpdateUserSettings(Long id, UserSettingsDto userSettingsDto) {
        userSettingsDto.setId(id);

        UserSettings mappedUserSettings = userSettingsMapper.mapFrom(userSettingsDto);

        return userSettingsRepository.findById(id).map(existingUserSettings -> {
            Optional.ofNullable(mappedUserSettings.getUser()).ifPresent(existingUserSettings::setUser);
            Optional.ofNullable(mappedUserSettings.getShowFriends()).ifPresent(existingUserSettings::setShowFriends);
            Optional.ofNullable(mappedUserSettings.getShowProfilePicture()).ifPresent(existingUserSettings::setShowProfilePicture);
            Optional.ofNullable(mappedUserSettings.getShowBio()).ifPresent(existingUserSettings::setShowBio);
            Optional.ofNullable(mappedUserSettings.getShowPosts()).ifPresent(existingUserSettings::setShowPosts);
            Optional.ofNullable(mappedUserSettings.getShowLikes()).ifPresent(existingUserSettings::setShowLikes);
            Optional.ofNullable(mappedUserSettings.getShowComments()).ifPresent(existingUserSettings::setShowComments);
            return userSettingsMapper.mapTo(userSettingsRepository.save(existingUserSettings));
        }).orElseThrow(() -> new UserSettingsNotFoundException(id));
    }

    public void deleteUserSettings(Long id) {
        userSettingsRepository.deleteById(id);
    }
}
