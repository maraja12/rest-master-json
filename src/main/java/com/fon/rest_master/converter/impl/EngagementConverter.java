package com.fon.rest_master.converter.impl;

import com.fon.rest_master.converter.DtoEntityConverter;
import com.fon.rest_master.domain.Engagement;
import com.fon.rest_master.domain.EngagementId;
import com.fon.rest_master.dto.EngagementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EngagementConverter implements DtoEntityConverter<EngagementDto, Engagement> {

    @Autowired
    private ProjectConverter projectConverter;
    @Autowired
    private EmployeeConverter employeeConverter;

    @Override
    public EngagementDto toDto(Engagement engagement) {
        return EngagementDto.builder()
                .projectDto(projectConverter.toDto(engagement.getId().getProject()))
                .employeeDto(employeeConverter.toDto(engagement.getId().getEmployee()))
                .month(engagement.getId().getMonth())
                .year(engagement.getId().getYear())
                .role(engagement.getRole())
                .numOfHours(engagement.getNumOfHours())
                .build();
    }

    @Override
    public Engagement toEntity(EngagementDto engagementDto) {
        return Engagement.builder()
                .id(EngagementId.builder()
                        .project(projectConverter.toEntity(engagementDto.getProjectDto()))
                        .employee(employeeConverter.toEntity(engagementDto.getEmployeeDto()))
                        .month(engagementDto.getMonth())
                        .year(engagementDto.getYear())
                        .build())
                .role(engagementDto.getRole())
                .numOfHours(engagementDto.getNumOfHours())
                .build();
    }
}
