package com.fon.rest_master.controller;

import com.fon.rest_master.dto.ProjectDto;
import com.fon.rest_master.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController{

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    ResponseEntity<List<ProjectDto>> getAll(){
        List<ProjectDto> projects = projectService.getAll();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    ResponseEntity<ProjectDto> getById(@PathVariable Long id){
        ProjectDto project = projectService.getById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<ProjectDto> save(@RequestBody @Valid ProjectDto project){
        ProjectDto projectSaved = projectService.save(project);
        return new ResponseEntity<>(projectSaved, HttpStatus.CREATED);
    }
    @PutMapping()
    ResponseEntity<ProjectDto> update(@RequestBody @Valid ProjectDto project){
        ProjectDto projectUpdated = projectService.update(project);
        return new ResponseEntity<>(projectUpdated, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id){
        projectService.delete(id);
        return new ResponseEntity<>("Project with id = " + id + " is deleted", HttpStatus.OK);
    }
}
