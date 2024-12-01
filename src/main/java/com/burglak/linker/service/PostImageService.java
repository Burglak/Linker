package com.burglak.linker.service;

import com.burglak.linker.dto.PostImageDto;
import com.burglak.linker.exception.PostImageNotFoundException;
import com.burglak.linker.mapper.impl.PostImageMapper;
import com.burglak.linker.model.PostImage;
import com.burglak.linker.repository.PostImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class PostImageService {

    private PostImageRepository postImageRepository;

    private PostImageMapper postImageMapper;

    public PostImageService(PostImageRepository postImageRepository, PostImageMapper postImageMapper) {
        this.postImageRepository = postImageRepository;
        this.postImageMapper = postImageMapper;
    }

    public PostImageDto createPostImage(PostImageDto postImageDto) {
        PostImage postImage = postImageMapper.mapFrom(postImageDto);
        PostImage createdPostImage = postImageRepository.save(postImage);
        return postImageMapper.mapTo(createdPostImage);
    }

    public PostImageDto findPostImageById(Long id) {
        PostImage postImage = postImageRepository.findById(id).orElseThrow(() -> new PostImageNotFoundException(id));
        return postImageMapper.mapTo(postImage);
    }

    public List<PostImageDto> findAllPostImages() {
        List<PostImage> postImages = StreamSupport.stream(postImageRepository.findAll().spliterator(), false).toList();
        return postImages.stream().map(postImageMapper::mapTo).toList();
    }

    public PostImageDto updatePostImage(Long id, PostImageDto postImageDto) {
        postImageDto.setId(id);
        PostImage existingPostImage = postImageRepository.findById(id).orElseThrow(() -> new PostImageNotFoundException(id));
        existingPostImage = postImageMapper.mapFrom(postImageDto);
        PostImage savedPostImage = postImageRepository.save(existingPostImage);
        return postImageMapper.mapTo(savedPostImage);
    }

    public PostImageDto partialUpdatePostImage(Long id, PostImageDto postImageDto) {
        postImageDto.setId(id);

        PostImage mappedPostImage = postImageMapper.mapFrom(postImageDto);

        return postImageRepository.findById(id).map(existingPostImage -> {
            Optional.ofNullable(mappedPostImage.getPost()).ifPresent(existingPostImage::setPost);
            Optional.ofNullable(mappedPostImage.getImagePath()).ifPresent(existingPostImage::setImagePath);
            Optional.ofNullable(mappedPostImage.getIsPrimary()).ifPresent(existingPostImage::setIsPrimary);
            return postImageMapper.mapTo(postImageRepository.save(existingPostImage));
        }).orElseThrow(() -> new PostImageNotFoundException(id));
    }

    public void deletePostImage(Long id) {
        postImageRepository.deleteById(id);
    }
}
