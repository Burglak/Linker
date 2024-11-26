package com.burglak.linker.mapper.impl;

import com.burglak.linker.dto.UserThemeDto;
import com.burglak.linker.mapper.Mapper;
import com.burglak.linker.model.entity.UserTheme;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserThemeMapper implements Mapper<UserTheme, UserThemeDto> {

    private ModelMapper modelMapper;

    public UserThemeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserThemeDto mapTo(UserTheme userTheme) {
        return modelMapper.map(userTheme, UserThemeDto.class);
    }

    @Override
    public UserTheme mapFrom(UserThemeDto userThemeDto) {
        return modelMapper.map(userThemeDto, UserTheme.class);
    }

}
