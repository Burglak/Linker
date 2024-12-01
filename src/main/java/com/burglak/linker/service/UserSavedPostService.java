package com.burglak.linker.service;

import com.burglak.linker.dto.UserSavedPostDto;
import com.burglak.linker.exception.UserSavedPostNotFoundException;
import com.burglak.linker.mapper.impl.UserSavedPostMapper;
import com.burglak.linker.model.UserSavedPost;
import com.burglak.linker.repository.UserSavedPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UserSavedPostService {

    private UserSavedPostRepository userSavedPostRepository;

    private UserSavedPostMapper userSavedPostMapper;

    public UserSavedPostService(UserSavedPostRepository userSavedPostRepository, UserSavedPostMapper userSavedPostMapper) {
        this.userSavedPostRepository = userSavedPostRepository;
        this.userSavedPostMapper = userSavedPostMapper;
    }

    public UserSavedPostDto createUserSavedPost(UserSavedPostDto userSavedPostDto) {
        UserSavedPost userSavedPost = userSavedPostMapper.mapFrom(userSavedPostDto);
        UserSavedPost createdUserSavedPost = userSavedPostRepository.save(userSavedPost);
        return userSavedPostMapper.mapTo(createdUserSavedPost);
    }

    public UserSavedPostDto findUserSavedPostById(Long id) {
        UserSavedPost userSavedPost = userSavedPostRepository.findById(id).orElseThrow(() -> new UserSavedPostNotFoundException(id));
        return userSavedPostMapper.mapTo(userSavedPost);
    }

    public List<UserSavedPostDto> findAllUserSavedPosts() {
        List<UserSavedPost> userSavedPosts = StreamSupport.stream(userSavedPostRepository.findAll().spliterator(), false).toList();
        return userSavedPosts.stream().map(userSavedPostMapper::mapTo).toList();
    }

    public UserSavedPostDto updateUserSavedPost(Long id, UserSavedPostDto userSavedPostDto) {
        userSavedPostDto.setId(id);
        UserSavedPost existingUserSavedPost = userSavedPostRepository.findById(id).orElseThrow(() -> new UserSavedPostNotFoundException(id));
        existingUserSavedPost = userSavedPostMapper.mapFrom(userSavedPostDto);
        UserSavedPost savedUserSavedPost = userSavedPostRepository.save(existingUserSavedPost);
        return userSavedPostMapper.mapTo(savedUserSavedPost);
    }

    public UserSavedPostDto partialUpdateUserSavedPost(Long id, UserSavedPostDto userSavedPostDto) {
        userSavedPostDto.setId(id);

        UserSavedPost mappedUserSavedPost = userSavedPostMapper.mapFrom(userSavedPostDto);

        return userSavedPostRepository.findById(id).map(existingUserSavedPost -> {
            Optional.ofNullable(mappedUserSavedPost.getUser()).ifPresent(existingUserSavedPost::setUser);
            Optional.ofNullable(mappedUserSavedPost.getPost()).ifPresent(existingUserSavedPost::setPost);
            return userSavedPostMapper.mapTo(userSavedPostRepository.save(existingUserSavedPost));
        }).orElseThrow(() -> new UserSavedPostNotFoundException(id));
    }

    public void deleteUserSavedPost(Long id) {
        userSavedPostRepository.deleteById(id);
    }

}
