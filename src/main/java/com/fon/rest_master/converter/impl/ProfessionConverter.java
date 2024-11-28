package com.fon.rest_master.converter.impl;

import com.fon.rest_master.converter.DtoEntityConverter;
import com.fon.rest_master.domain.Profession;
import com.fon.rest_master.dto.ProfessionDto;
import org.springframework.stereotype.Component;

@Component
public class ProfessionConverter implements DtoEntityConverter<ProfessionDto, Profession> {
    @Override
    public ProfessionDto toDto(Profession profession) {
        return ProfessionDto.builder()
                .id(profession.getId())
                .name(profession.getName())
                .build();
    }

    @Override
    public Profession toEntity(ProfessionDto professionDto) {
        return Profession.builder()
                .id(professionDto.getId())
                .name(professionDto.getName())
                .build();
    }
}
