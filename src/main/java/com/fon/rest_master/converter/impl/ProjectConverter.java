package com.fon.rest_master.converter.impl;

import com.fon.rest_master.converter.DtoEntityConverter;
import com.fon.rest_master.domain.Project;
import com.fon.rest_master.dto.ProjectDto;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter implements DtoEntityConverter<ProjectDto, Project> {
    @Override
    public ProjectDto toDto(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .budget(project.getBudget())
                .build();
    }

    @Override
    public Project toEntity(ProjectDto projectDto) {
        return Project.builder()
                .id(projectDto.getId())
                .name(projectDto.getName())
                .budget(projectDto.getBudget())
                .build();
    }
}
