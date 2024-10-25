package com.burglak.linker.service;

import com.burglak.linker.dto.PostCommentDto;
import com.burglak.linker.exception.PostCommentNotFoundException;
import com.burglak.linker.mapper.impl.PostCommentMapper;
import com.burglak.linker.model.entity.PostComment;
import com.burglak.linker.repository.PostCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class PostCommentService {

    private PostCommentRepository postCommentRepository;

    private PostCommentMapper postCommentMapper;

    public PostCommentService(PostCommentRepository postCommentRepository, PostCommentMapper postCommentMapper) {
        this.postCommentRepository = postCommentRepository;
        this.postCommentMapper = postCommentMapper;
    }

    public PostCommentDto createPostComment(PostCommentDto postCommentDto) {
        PostComment postComment = postCommentMapper.mapFrom(postCommentDto);
        PostComment createdPostComment = postCommentRepository.save(postComment);
        return postCommentMapper.mapTo(createdPostComment);
    }

    public PostCommentDto findPostCommentById(Long id) {
        PostComment postComment = postCommentRepository.findById(id).orElseThrow(() -> new PostCommentNotFoundException(id));
        return postCommentMapper.mapTo(postComment);
    }

    public List<PostCommentDto> findAllPostComments() {
        List<PostComment> postComments = StreamSupport.stream(postCommentRepository.findAll().spliterator(), false).toList();
        List<PostCommentDto> postCommentsDto = postComments.stream().map(postComment -> postCommentMapper.mapTo(postComment)).toList();
        return postCommentsDto;
    }

    public PostCommentDto updatePostComment(Long id, PostCommentDto postCommentDto) {
        postCommentDto.setId(id);

        PostComment existingPostComment = postCommentRepository.findById(id).orElseThrow(() -> new PostCommentNotFoundException(id));
        existingPostComment = postCommentMapper.mapFrom(postCommentDto);
        PostComment updatedPostComment = postCommentRepository.save(existingPostComment);
        return postCommentMapper.mapTo(updatedPostComment);
    }

    public PostCommentDto partialUpdatePostComment(Long id, PostCommentDto postCommentDto) {
        postCommentDto.setId(id);

        PostComment mappedPostComment = postCommentMapper.mapFrom(postCommentDto);

        return postCommentRepository.findById(id).map(existingPostComment -> {
            Optional.ofNullable(mappedPostComment.getPost()).ifPresent(existingPostComment::setPost);
            Optional.ofNullable(mappedPostComment.getUser()).ifPresent(existingPostComment::setUser);
            Optional.ofNullable(mappedPostComment.getContent()).ifPresent(existingPostComment::setContent);
            Optional.ofNullable(mappedPostComment.getParentComment()).ifPresent(existingPostComment::setParentComment);
            return postCommentMapper.mapTo(postCommentRepository.save(existingPostComment));
        }).orElseThrow(() -> new PostCommentNotFoundException(id));
    }

    public void deletePostComment(Long id) {
        postCommentRepository.deleteById(id);
    }
}
