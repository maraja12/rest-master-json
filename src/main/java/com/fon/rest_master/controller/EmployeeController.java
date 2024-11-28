package com.fon.rest_master.controller;

import com.fon.rest_master.dto.EmployeeDto;
import com.fon.rest_master.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll(){
        List<EmployeeDto> employees = employeeService.getAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Long id){
        EmployeeDto employee = employeeService.getById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<EmployeeDto> save(@Valid @RequestBody EmployeeDto employee){
        EmployeeDto employeeSaved = employeeService.save(employee);
        return new ResponseEntity<>(employeeSaved, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<EmployeeDto> update(@RequestBody @Valid EmployeeDto employee){
        EmployeeDto employeeUpdated = employeeService.update(employee);
        return new ResponseEntity<>(employeeUpdated, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        employeeService.delete(id);
        return new ResponseEntity<>("Employee with id = " + id + " is deleted", HttpStatus.OK);
    }
}
