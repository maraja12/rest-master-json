package com.fon.rest_master.controller;

import com.fon.rest_master.dto.CompanyDto;
import com.fon.rest_master.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAll(){
        List<CompanyDto> companyDtos = companyService.getAll();
        return new ResponseEntity<>(companyDtos, HttpStatus.OK);
    }
    @GetMapping("/{pib}")
    public ResponseEntity<CompanyDto> getById(@PathVariable int pib){
        CompanyDto companyDto = companyService.getById(pib);
        return new ResponseEntity<>(companyDto, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CompanyDto> save(@RequestBody @Valid CompanyDto companyDto){
        CompanyDto companyDtoSaved = companyService.save(companyDto);
        return new ResponseEntity<>(companyDtoSaved, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<CompanyDto> update(@RequestBody @Valid CompanyDto companyDto){
        CompanyDto companyDtoUpdated = companyService.update(companyDto);
        return new ResponseEntity<>(companyDtoUpdated, HttpStatus.OK);
    }
    @DeleteMapping("/{pib}")
    public ResponseEntity<String> delete(int pib){
        companyService.delete(pib);
        return new ResponseEntity<>("Company with pib = " + pib + " is deleted", HttpStatus.OK);
    }
    @GetMapping("/invoices/{company-name}")
    public ResponseEntity<Object> getUnpaidInvoicesByCompany(@PathVariable("company-name") String companyName){
        Object list = companyService.getUnpaidInvoicesByCompany(companyName);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
