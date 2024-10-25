package com.burglak.linker.service;

import com.burglak.linker.dto.PostReactionDto;
import com.burglak.linker.exception.PostReactionNotFoundException;
import com.burglak.linker.mapper.impl.PostReactionMapper;
import com.burglak.linker.model.entity.PostReaction;
import com.burglak.linker.repository.PostReactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class PostReactionService {

    private PostReactionRepository postReactionRepository;

    private PostReactionMapper postReactionMapper;

    public PostReactionService(PostReactionRepository postReactionRepository, PostReactionMapper postReactionMapper) {
        this.postReactionRepository = postReactionRepository;
        this.postReactionMapper = postReactionMapper;
    }

    public PostReactionDto createPostReaction(PostReactionDto postReactionDto) {
        PostReaction postReaction = postReactionMapper.mapFrom(postReactionDto);
        PostReaction createdPostReaction = postReactionRepository.save(postReaction);
        return postReactionMapper.mapTo(createdPostReaction);
    }

    public PostReactionDto findPostReactionById(Long id) {
        PostReaction postReaction = postReactionRepository.findById(id).orElseThrow(() -> new PostReactionNotFoundException(id));
        return postReactionMapper.mapTo(postReaction);
    }

    public List<PostReactionDto> findAllPostReactions() {
        List<PostReaction> postReactions = StreamSupport.stream(postReactionRepository.findAll().spliterator(), false).toList();
        return postReactions.stream().map(postReactionMapper::mapTo).toList();
    }

    public PostReactionDto updatePostReaction(Long id, PostReactionDto postReactionDto) {
        postReactionDto.setId(id);
        PostReaction existingPostReaction = postReactionRepository.findById(id).orElseThrow(() -> new PostReactionNotFoundException(id));
        existingPostReaction = postReactionMapper.mapFrom(postReactionDto);
        PostReaction savedPostReaction = postReactionRepository.save(existingPostReaction);
        return postReactionMapper.mapTo(savedPostReaction);
    }

    public PostReactionDto partialUpdatePostReaction(Long id, PostReactionDto postReactionDto) {
        postReactionDto.setId(id);

        PostReaction mappedPostReaction = postReactionMapper.mapFrom(postReactionDto);

        return postReactionRepository.findById(id).map(existingPostReaction -> {
            Optional.ofNullable(mappedPostReaction.getPost()).ifPresent(mappedPostReaction::setPost);
            Optional.ofNullable(mappedPostReaction.getUser()).ifPresent(mappedPostReaction::setUser);
            Optional.ofNullable(mappedPostReaction.getPostReactionType()).ifPresent(mappedPostReaction::setPostReactionType);
            return postReactionMapper.mapTo(postReactionRepository.save(existingPostReaction));
        }).orElseThrow(() -> new PostReactionNotFoundException(id));
    }

    public void deletePostReaction(Long id) {
        postReactionRepository.deleteById(id);
    }
}
