package com.burglak.linker.mapper.impl;

import com.burglak.linker.dto.PostReportDto;
import com.burglak.linker.mapper.Mapper;
import com.burglak.linker.model.entity.PostReport;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostReportMapper implements Mapper<PostReport, PostReportDto> {

    private ModelMapper modelMapper;

    public PostReportMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PostReportDto mapTo(PostReport postReport) {
        return modelMapper.map(postReport, PostReportDto.class);
    }

    @Override
    public PostReport mapFrom(PostReportDto postReportDto) {
        return modelMapper.map(postReportDto, PostReport.class);
    }

}
