package com.burglak.linker.controller;

import com.burglak.linker.dto.PostReportDto;
import com.burglak.linker.service.PostReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post-reports")
public class PostReportController {

    private final PostReportService postReportService;

    public PostReportController(PostReportService postReportService) {
        this.postReportService = postReportService;
    }

    @PostMapping
    public ResponseEntity<PostReportDto> createPostReport(@RequestBody PostReportDto postReportDto) {
        return new ResponseEntity<>(postReportService.createPostReport(postReportDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostReportDto> getPostReports() {
        return postReportService.findAllPostReports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostReportDto> getPostReport(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postReportService.findPostReportById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public PostReportDto updatePostReport(@PathVariable("id") Long id, @RequestBody PostReportDto postReportDto) {
        return postReportService.updatePostReport(id, postReportDto);
    }

    @PatchMapping("/{id}")
    public PostReportDto partialUpdatePostReport(@PathVariable("id") Long id, @RequestBody PostReportDto postReportDto) {
        return postReportService.partialUpdatePostReport(id, postReportDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostReport(@PathVariable("id") Long id) {
        postReportService.deletePostReport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
