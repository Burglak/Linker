package com.burglak.linker.service;

import com.burglak.linker.dto.UserStatusDto;
import com.burglak.linker.exception.UserSettingsNotFoundException;
import com.burglak.linker.exception.UserStatusNotFoundException;
import com.burglak.linker.mapper.impl.UserStatusMapper;
import com.burglak.linker.model.UserStatus;
import com.burglak.linker.repository.UserStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UserStatusService {

    private UserStatusRepository userStatusRepository;

    private UserStatusMapper userStatusMapper;

    public UserStatusService(UserStatusRepository userStatusRepository, UserStatusMapper userStatusMapper) {
        this.userStatusRepository = userStatusRepository;
        this.userStatusMapper = userStatusMapper;
    }

    public UserStatusDto createUserStatus(UserStatusDto userStatusDto) {
        UserStatus userStatus = userStatusMapper.mapFrom(userStatusDto);
        UserStatus createdUserStatus = userStatusRepository.save(userStatus);
        return userStatusMapper.mapTo(createdUserStatus);
    }

    public UserStatusDto findUserStatusById(Long id) {
        UserStatus userStatus = userStatusRepository.findById(id).orElseThrow(() -> new UserStatusNotFoundException(id));
        return userStatusMapper.mapTo(userStatus);
    }

    public List<UserStatusDto> findAllUserStatuses() {
        List<UserStatus> userStatuses = StreamSupport.stream(userStatusRepository.findAll().spliterator(), false).toList();
        return userStatuses.stream().map(userStatusMapper::mapTo).toList();
    }

    public UserStatusDto updateUserStatus(Long id, UserStatusDto userStatusDto) {
        userStatusDto.setId(id);
        UserStatus existingUserStatus = userStatusRepository.findById(id).orElseThrow(() -> new UserStatusNotFoundException(id));
        existingUserStatus = userStatusMapper.mapFrom(userStatusDto);
        UserStatus savedUserStatus = userStatusRepository.save(existingUserStatus);
        return userStatusMapper.mapTo(savedUserStatus);
    }

    public UserStatusDto partialUpdateUserStatus(Long id, UserStatusDto userStatusDto) {
        userStatusDto.setId(id);

        UserStatus mappedUserStatus = userStatusMapper.mapFrom(userStatusDto);

        return userStatusRepository.findById(id).map(existingUserStatus -> {
            Optional.ofNullable(mappedUserStatus.getUser()).ifPresent(existingUserStatus::setUser);
            Optional.ofNullable(mappedUserStatus.getIsSuspended()).ifPresent(existingUserStatus::setIsSuspended);
            Optional.ofNullable(mappedUserStatus.getSuspendedUntil()).ifPresent(existingUserStatus::setSuspendedUntil);
            Optional.ofNullable(mappedUserStatus.getIsVerified()).ifPresent(existingUserStatus::setIsVerified);
            return userStatusMapper.mapTo(userStatusRepository.save(existingUserStatus));
        }).orElseThrow(() -> new UserSettingsNotFoundException(id));
    }

    public void deleteUserStatus(Long id) {
        userStatusRepository.deleteById(id);
    }

}
