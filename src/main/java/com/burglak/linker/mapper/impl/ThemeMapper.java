package com.burglak.linker.mapper.impl;

import com.burglak.linker.dto.ThemeDto;
import com.burglak.linker.mapper.Mapper;
import com.burglak.linker.model.entity.Theme;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ThemeMapper implements Mapper<Theme, ThemeDto> {

    private ModelMapper modelMapper;

    public ThemeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ThemeDto mapTo(Theme theme) {
        return modelMapper.map(theme, ThemeDto.class);
    }

    @Override
    public Theme mapFrom(ThemeDto themeDto) {
        return modelMapper.map(themeDto, Theme.class);
    }

}
