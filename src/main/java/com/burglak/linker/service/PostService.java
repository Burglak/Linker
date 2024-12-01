package com.burglak.linker.service;

import com.burglak.linker.dto.PostDto;
import com.burglak.linker.dto.UserDto;
import com.burglak.linker.enums.UserActivityType;
import com.burglak.linker.exception.PostNotFoundException;
import com.burglak.linker.exception.UserNotFoundException;
import com.burglak.linker.mapper.impl.PostMapper;
import com.burglak.linker.model.Post;
import com.burglak.linker.model.User;
import com.burglak.linker.repository.PostRepository;
import com.burglak.linker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class PostService {

    private PostRepository postRepository;

    private PostMapper postMapper;

    private UserActivityService userActivityService;

    private UserRepository userRepository;

    public PostService(PostRepository postRepository, PostMapper postMapper, UserActivityService userActivityService, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.userActivityService = userActivityService;
        this.userRepository = userRepository;
    }

    public PostDto createPost(PostDto postDto) {
        User user = userRepository.findById(postDto.getUser().getId())
                .orElseThrow(() -> new UserNotFoundException(postDto.getUser().getId()));
        Post post = postMapper.mapFrom(postDto);
        post.setUser(user);
        userActivityService.updateUserActivity(postDto.getUser(), UserActivityType.POST); //update user's activity
        Post createdPost = postRepository.save(post);
        return postMapper.mapTo(createdPost);
    }

    public PostDto findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        return postMapper.mapTo(post);
    }

    public List<PostDto> findAllPosts() {
        List<Post> posts = StreamSupport.stream(postRepository.findAll().spliterator(), false).toList();
        List<PostDto> postsDto = posts.stream().map(post -> postMapper.mapTo(post)).toList();
        return postsDto;
    }

    public PostDto updatePost(Long id, PostDto postDto) {
        postDto.setId(id);

        Post existingPost = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        existingPost = postMapper.mapFrom(postDto);
        Post savedPost = postRepository.save(existingPost);
        return postMapper.mapTo(savedPost);
    }

    public PostDto partialUpdatePost(Long id, PostDto postDto) {
        postDto.setId(id);

        //mapping dto to entity for easier updating fields
        Post mappedPost = postMapper.mapFrom(postDto);

        return postRepository.findById(id).map(existingPost -> {
            Optional.ofNullable(mappedPost.getUser()).ifPresent(existingPost::setUser);
            Optional.ofNullable(mappedPost.getUpvotes()).ifPresent(existingPost::setUpvotes);
            Optional.ofNullable(mappedPost.getDownvotes()).ifPresent(existingPost::setDownvotes);
            Optional.ofNullable(mappedPost.getPostVisibility()).ifPresent(existingPost::setPostVisibility);
            Optional.ofNullable(mappedPost.getCategory()).ifPresent(existingPost::setCategory);
            return postMapper.mapTo(postRepository.save(existingPost));
        }).orElseThrow(() -> new PostNotFoundException(id));
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
