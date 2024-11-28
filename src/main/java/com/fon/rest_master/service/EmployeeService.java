package com.fon.rest_master.service;

import com.fon.rest_master.dto.EmployeeDto;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAll();
    EmployeeDto getById(Long id) throws EntityNotFoundException;
    EmployeeDto save(EmployeeDto employeeDto);
    EmployeeDto update(EmployeeDto employeeDto) throws EntityNotFoundException;
    void delete(Long id) throws EntityNotFoundException;
}
