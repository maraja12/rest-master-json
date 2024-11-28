package com.fon.rest_master.converter.impl;


import com.fon.rest_master.converter.DtoEntityConverter;
import com.fon.rest_master.domain.Employee;
import com.fon.rest_master.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter implements DtoEntityConverter<EmployeeDto, Employee> {

    @Autowired
    private ProfessionConverter professionConverter;

    @Override
    public EmployeeDto toDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .birthday(employee.getBirthday())
                .address(employee.getAddress())
                .email(employee.getEmail())
                .professionDto(professionConverter.toDto(employee.getProfession()))
                .build();
    }

    @Override
    public Employee toEntity(EmployeeDto employeeDto) {
        return Employee.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .surname(employeeDto.getSurname())
                .birthday(employeeDto.getBirthday())
                .address(employeeDto.getAddress())
                .email(employeeDto.getEmail())
                .profession(professionConverter.toEntity(employeeDto.getProfessionDto()))
                .build();
    }
}
