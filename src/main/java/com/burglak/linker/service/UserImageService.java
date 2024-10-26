package com.burglak.linker.service;

import com.burglak.linker.dto.UserImageDto;
import com.burglak.linker.exception.UserImageNotFoundException;
import com.burglak.linker.mapper.impl.UserImageMapper;
import com.burglak.linker.model.entity.UserImage;
import com.burglak.linker.repository.UserImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UserImageService {

    private UserImageRepository userImageRepository;

    private UserImageMapper userImageMapper;

    public UserImageService(UserImageRepository userImageRepository, UserImageMapper userImageMapper) {
        this.userImageRepository = userImageRepository;
        this.userImageMapper = userImageMapper;
    }

    public UserImageDto createUserImage(UserImageDto userImageDto) {
        UserImage userImage = userImageMapper.mapFrom(userImageDto);
        UserImage createdUserImage = userImageRepository.save(userImage);
        return userImageMapper.mapTo(createdUserImage);
    }

    public UserImageDto findUserImageById(Long id) {
        UserImage userImage = userImageRepository.findById(id).orElseThrow(() -> new UserImageNotFoundException(id));
        return userImageMapper.mapTo(userImage);
    }

    public List<UserImageDto> findAllUserImages() {
        List<UserImage> userImages = StreamSupport.stream(userImageRepository.findAll().spliterator(), false).toList();
        return userImages.stream().map(userImageMapper::mapTo).toList();
    }

    public UserImageDto updateUserImage(Long id, UserImageDto userImageDto) {
        userImageDto.setId(id);
        UserImage existingUserImage = userImageRepository.findById(id).orElseThrow(() -> new UserImageNotFoundException(id));
        existingUserImage = userImageMapper.mapFrom(userImageDto);
        UserImage savedUserImage = userImageRepository.save(existingUserImage);
        return userImageMapper.mapTo(savedUserImage);
    }

    public UserImageDto partialUpdateUserImage(Long id, UserImageDto userImageDto) {
        userImageDto.setId(id);

        UserImage mappedUserImage = userImageMapper.mapFrom(userImageDto);

        return userImageRepository.findById(id).map(existingUserImage -> {
            Optional.ofNullable(mappedUserImage.getUser()).ifPresent(existingUserImage::setUser);
            Optional.ofNullable(mappedUserImage.getPath()).ifPresent(existingUserImage::setPath);
            Optional.ofNullable(mappedUserImage.getUserImageVisibility()).ifPresent(existingUserImage::setUserImageVisibility);
            return userImageMapper.mapTo(userImageRepository.save(existingUserImage));
        }).orElseThrow(() -> new UserImageNotFoundException(id));
    }

    public void deleteUserImage(Long id) {
        userImageRepository.deleteById(id);
    }

}
