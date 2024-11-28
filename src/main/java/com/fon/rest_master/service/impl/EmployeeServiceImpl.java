package com.fon.rest_master.service.impl;

import com.fon.rest_master.converter.impl.EmployeeConverter;
import com.fon.rest_master.converter.impl.ProfessionConverter;
import com.fon.rest_master.domain.Employee;
import com.fon.rest_master.domain.Profession;
import com.fon.rest_master.dto.EmployeeDto;
import com.fon.rest_master.repository.EmployeeRepository;
import com.fon.rest_master.repository.ProfessionRepository;
import com.fon.rest_master.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeConverter employeeConverter;
    private ProfessionRepository professionRepository;
    private ProfessionConverter professionConverter;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeConverter employeeConverter,
                               ProfessionRepository professionRepository, ProfessionConverter professionConverter) {
        this.employeeRepository = employeeRepository;
        this.employeeConverter = employeeConverter;
        this.professionRepository = professionRepository;
        this.professionConverter = professionConverter;
    }

    @Override
    public List<EmployeeDto> getAll() {
        return employeeRepository.findAll().stream()
                .map(entity -> employeeConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getById(Long id) throws EntityNotFoundException {
        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            return employeeConverter.toDto(employee);
        }
        else{
            throw new EntityNotFoundException("Employee with id = " + id +" is not found");
        }
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        Employee employee = employeeConverter.toEntity(employeeDto);
        employee = employeeRepository.save(employee);
        return employeeConverter.toDto(employee);
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) throws EntityNotFoundException {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeDto.getId());
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            Optional<Profession> professionOpt = professionRepository.findById(employeeDto.getProfessionDto().getId());
            if(professionOpt.isPresent()){
                employee.setProfession(professionConverter.toEntity(employeeDto.getProfessionDto()));

                employee.setName(employeeDto.getName());
                employee.setSurname(employeeDto.getSurname());
                employee.setBirthday(employeeDto.getBirthday());
                employee.setAddress(employeeDto.getAddress());
                employee.setEmail(employeeDto.getEmail());
                employee = employeeRepository.save(employee);
                return employeeConverter.toDto(employee);
            }else{
                throw new EntityNotFoundException("Profession with id = " + " is not found");
            }
        }
        else{
            throw new EntityNotFoundException("Employee with id = " + employeeDto.getId() + " is not found");
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            employeeRepository.delete(employee);
        }
        else{
            throw new EntityNotFoundException("Employee with id = " + id +" is not found");
        }
    }
}
