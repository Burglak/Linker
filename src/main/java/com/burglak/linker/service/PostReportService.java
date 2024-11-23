package com.burglak.linker.service;

import com.burglak.linker.dto.PostReportDto;
import com.burglak.linker.exception.PostReportNotFoundException;
import com.burglak.linker.mapper.impl.PostReportMapper;
import com.burglak.linker.model.entity.PostReport;
import com.burglak.linker.repository.PostReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class PostReportService {

    private PostReportRepository postReportRepository;

    private PostReportMapper postReportMapper;

    public PostReportService(PostReportRepository postReportRepository, PostReportMapper postReportMapper) {
        this.postReportRepository = postReportRepository;
        this.postReportMapper = postReportMapper;
    }

    public PostReportDto createPostReport(PostReportDto postReportDto) {
        PostReport postReport = postReportMapper.mapFrom(postReportDto);
        PostReport createdPostReport = postReportRepository.save(postReport);
        return postReportMapper.mapTo(createdPostReport);
    }

    public PostReportDto findPostReportById(Long id) {
        PostReport postReport = postReportRepository.findById(id).orElseThrow(() -> new PostReportNotFoundException(id));
        return postReportMapper.mapTo(postReport);
    }

    public List<PostReportDto> findAllPostReports() {
        List<PostReport> postReports = StreamSupport.stream(postReportRepository.findAll().spliterator(), false).toList();
        return postReports.stream().map(postReportMapper::mapTo).toList();
    }

    public PostReportDto updatePostReport(Long id, PostReportDto postReportDto) {
        postReportDto.setId(id);
        PostReport existingPostReport = postReportRepository.findById(id).orElseThrow(() -> new PostReportNotFoundException(id));
        existingPostReport = postReportMapper.mapFrom(postReportDto);
        PostReport savedPostReport = postReportRepository.save(existingPostReport);
        return postReportMapper.mapTo(savedPostReport);
    }

    public PostReportDto partialUpdatePostReport(Long id, PostReportDto postReportDto) {
        postReportDto.setId(id);

        PostReport mappedPostReport = postReportMapper.mapFrom(postReportDto);

        return postReportRepository.findById(id).map(existingPostReport -> {
            Optional.ofNullable(mappedPostReport.getPost()).ifPresent(existingPostReport::setPost);
            Optional.ofNullable(mappedPostReport.getUser()).ifPresent(existingPostReport::setUser);
            Optional.ofNullable(mappedPostReport.getReportType()).ifPresent(existingPostReport::setReportType);
            return postReportMapper.mapTo(postReportRepository.save(existingPostReport));
        }).orElseThrow(() -> new PostReportNotFoundException(id));
    }

    public void deletePostReport(Long id) {
        postReportRepository.deleteById(id);
    }

}
