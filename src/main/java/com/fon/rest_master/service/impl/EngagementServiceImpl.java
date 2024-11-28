package com.fon.rest_master.service.impl;

import com.fon.rest_master.converter.impl.EngagementConverter;
import com.fon.rest_master.converter.impl.EmployeeConverter;
import com.fon.rest_master.converter.impl.ProjectConverter;
import com.fon.rest_master.domain.*;
import com.fon.rest_master.dto.EngagementDto;
import com.fon.rest_master.repository.EmployeeRepository;
import com.fon.rest_master.repository.EngagementRepository;
import com.fon.rest_master.repository.ProjectRepository;
import com.fon.rest_master.service.EngagementService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EngagementServiceImpl implements EngagementService {

    private EngagementRepository engagementRepository;
    private EngagementConverter engagementConverter;
    private ProjectRepository projectRepository;
    private ProjectConverter projectConverter;
    private EmployeeRepository employeeRepository;
    private EmployeeConverter employeeConverter;

    public EngagementServiceImpl(EngagementRepository engagementRepository,
                                 EngagementConverter engagementConverter,
                                 ProjectRepository projectRepository,
                                 ProjectConverter projectConverter,
                                 EmployeeRepository employeeRepository,
                                 EmployeeConverter employeeConverter) {
        this.engagementRepository = engagementRepository;
        this.engagementConverter = engagementConverter;
        this.projectRepository = projectRepository;
        this.projectConverter = projectConverter;
        this.employeeRepository = employeeRepository;
        this.employeeConverter = employeeConverter;
    }

    @Override
    public List<EngagementDto> getAll() {
        return engagementRepository.findAll().stream()
                .map(entity -> engagementConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    public Project findProjectById(Long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isEmpty()) {
            throw new EntityNotFoundException("Project with id = " + id + " is not found");
        }
        return projectOptional.get();
    }

    public Employee findEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) {
            throw new EntityNotFoundException("Employee with id = " + id + " is not found");
        }
        return employeeOptional.get();
    }

    @Override
    public EngagementDto getById(Long projectId, Long employeeId, Month month, int year)
            throws EntityNotFoundException {
        Project project = findProjectById(projectId);
        Employee employee = findEmployeeById(employeeId);
        EngagementId id = new EngagementId(project, employee, month, year);
        Optional<Engagement> engagementOpt = engagementRepository.findById(id);
        if (engagementOpt.isPresent()) {
            Engagement engagement = engagementOpt.get();
            return engagementConverter.toDto(engagement);
        }
        else{
            throw new EntityNotFoundException("Engagement with id = " + id + " is not found");
        }
    }

    @Override
    public EngagementDto save(EngagementDto engagementDto) {
        Optional<Project> projectOptional = projectRepository.findById(engagementDto.getProjectDto().getId());
        Optional<Employee> employeeOptional = employeeRepository.findById(engagementDto.getEmployeeDto().getId());
        if (projectOptional.isEmpty()) {
            throw new EntityNotFoundException("Project with id = " + engagementDto.getProjectDto().getId() +
                    " is not found");
        }
        if(employeeOptional.isEmpty()){
            throw new EntityNotFoundException("Employee with id = " + engagementDto.getEmployeeDto().getId() +
                    " is not found");
        }
        Engagement engagement = engagementConverter.toEntity(engagementDto);
        engagement = engagementRepository.save(engagement);
        return engagementConverter.toDto(engagement);
    }

    @Override
    public EngagementDto update(EngagementDto engagementDto) throws EntityNotFoundException {
        Project project = findProjectById(engagementDto.getProjectDto().getId());
        Employee employee = findEmployeeById(engagementDto.getEmployeeDto().getId());
        EngagementId engagementId = new EngagementId(
                project, employee, engagementDto.getMonth(), engagementDto.getYear());
        Optional<Engagement> engagementOptional = engagementRepository.findById(engagementId);
        if (engagementOptional.isEmpty()) {
            throw new EntityNotFoundException("Engagement with id = " + engagementId + " is not found");
        }
        Engagement engagement = engagementConverter.toEntity(engagementDto);
        engagement.setId(engagementId);
        engagement.setRole(engagementDto.getRole());
        engagement.setNumOfHours(engagementDto.getNumOfHours());
        engagement = engagementRepository.save(engagement);
        return engagementConverter.toDto(engagement);
    }

    @Override
    public void delete(Long projectId, Long employeeId, Month month, int year) throws EntityNotFoundException {
        Project project = findProjectById(projectId);
        Employee employee = findEmployeeById(employeeId);
        EngagementId id = new EngagementId(project, employee, month, year);
        Optional<Engagement> engagementOpt = engagementRepository.findById(id);
        if (engagementOpt.isPresent()) {
            Engagement engagement = engagementOpt.get();
            engagementRepository.delete(engagement);
        }
        else{
            throw new EntityNotFoundException("Engagement with id = " + id + " is not found");
        }
    }
}
