package com.fon.rest_master.service.impl;

import com.fon.rest_master.converter.impl.ProjectConverter;
import com.fon.rest_master.domain.Project;
import com.fon.rest_master.dto.ProjectDto;
import com.fon.rest_master.repository.ProjectRepository;
import com.fon.rest_master.service.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private ProjectConverter projectConverter;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectConverter projectConverter) {
        this.projectRepository = projectRepository;
        this.projectConverter = projectConverter;
    }

    @Override
    public List<ProjectDto> getAll() {
        return projectRepository.findAll().stream()
                .map(entity -> projectConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto getById(Long id) throws EntityNotFoundException {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            return projectConverter.toDto(project);
        }
        else{
            throw new EntityNotFoundException("Project with id = " + id + " is not found");
        }
    }

    @Override
    public ProjectDto save(ProjectDto projectDto) {
        Project project = projectConverter.toEntity(projectDto);
        project = projectRepository.save(project);
        return projectConverter.toDto(project);
    }

    @Override
    public ProjectDto update(ProjectDto projectDto) throws EntityNotFoundException {
        Optional<Project> projectOptional = projectRepository.findById(projectDto.getId());
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            project.setName(projectDto.getName());
            project.setBudget(projectDto.getBudget());
            project = projectRepository.save(project);
            return projectConverter.toDto(project);
        }
        else{
            throw new EntityNotFoundException("Project with id = " + projectDto.getId() + " is not found");
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            projectRepository.delete(project);
        }
        else{
            throw new EntityNotFoundException("Project with id = " + id + " is not found");
        }
    }
}
