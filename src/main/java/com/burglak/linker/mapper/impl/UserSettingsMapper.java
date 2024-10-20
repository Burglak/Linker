package com.burglak.linker.mapper.impl;

import com.burglak.linker.dto.UserSettingsDto;
import com.burglak.linker.mapper.Mapper;
import com.burglak.linker.model.entity.UserSettings;
import org.modelmapper.ModelMapper;

public class UserSettingsMapper implements Mapper<UserSettings, UserSettingsDto> {

    ModelMapper modelMapper;

    public UserSettingsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserSettingsDto mapTo(UserSettings userSettings) {
        return modelMapper.map(userSettings, UserSettingsDto.class);
    }

    @Override
    public UserSettings mapFrom(UserSettingsDto userSettingsDto) {
        return modelMapper.map(userSettingsDto, UserSettings.class);
    }

}
