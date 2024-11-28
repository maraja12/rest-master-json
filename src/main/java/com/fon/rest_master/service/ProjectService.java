package com.fon.rest_master.service;

import com.fon.rest_master.dto.ProjectDto;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> getAll();
    ProjectDto getById(Long id) throws EntityNotFoundException;
    ProjectDto save(ProjectDto projectDto);
    ProjectDto update(ProjectDto projectDto) throws EntityNotFoundException;
    void delete(Long id) throws EntityNotFoundException;
}
