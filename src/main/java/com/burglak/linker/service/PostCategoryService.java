package com.burglak.linker.service;

import com.burglak.linker.dto.PostCategoryDto;
import com.burglak.linker.exception.PostCategoryNotFoundException;
import com.burglak.linker.mapper.impl.PostCategoryMapper;
import com.burglak.linker.model.entity.PostCategory;
import com.burglak.linker.repository.PostCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class PostCategoryService {

    private PostCategoryRepository postCategoryRepository;

    private PostCategoryMapper postCategoryMapper;

    public PostCategoryService(PostCategoryRepository postCategoryRepository, PostCategoryMapper postCategoryMapper) {
        this.postCategoryRepository = postCategoryRepository;
        this.postCategoryMapper = postCategoryMapper;
    }

    public PostCategoryDto createPostCategory(PostCategoryDto postCategoryDto) {
        PostCategory postCategory = postCategoryMapper.mapFrom(postCategoryDto);
        PostCategory createdPostCategory = postCategoryRepository.save(postCategory);
        return postCategoryMapper.mapTo(createdPostCategory);
    }

    public PostCategoryDto findPostCategoryById(Long id) {
        PostCategory foundPost = postCategoryRepository.findById(id).orElseThrow(() -> new PostCategoryNotFoundException(id));
        return postCategoryMapper.mapTo(foundPost);
    }

    public List<PostCategoryDto> findAllPostCategories() {
        List<PostCategory> postCategories = StreamSupport.stream(postCategoryRepository.findAll().spliterator(), false).toList();
        List<PostCategoryDto> postCategoriesDto = postCategories.stream().map(postCategory -> postCategoryMapper.mapTo(postCategory)).toList();
        return postCategoriesDto;
    }

    public PostCategoryDto updateCategory(Long id, PostCategoryDto postCategoryDto) {
        postCategoryDto.setId(id);

        PostCategory existingPostCategory = postCategoryRepository.findById(id).orElseThrow(() -> new PostCategoryNotFoundException(id));
        existingPostCategory = postCategoryMapper.mapFrom(postCategoryDto);
        PostCategory savedPostCategory = postCategoryRepository.save(existingPostCategory);
        return postCategoryMapper.mapTo(savedPostCategory);
    }

    public PostCategoryDto partialUpdateCategory(Long id, PostCategoryDto postCategoryDto) {
        postCategoryDto.setId(id);

        return postCategoryRepository.findById(id).map(existingPostCategory -> {
            Optional.ofNullable(postCategoryDto.getName()).ifPresent(existingPostCategory::setName);
            return postCategoryMapper.mapTo(postCategoryRepository.save(existingPostCategory));
        }).orElseThrow(() -> new PostCategoryNotFoundException(id));
    }

    public void deletePostCategory(Long id) {
        postCategoryRepository.deleteById(id);
    }
}
