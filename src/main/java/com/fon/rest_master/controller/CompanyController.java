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
    public ResponseEntity<String> delete(@PathVariable int pib){
        companyService.delete(pib);
        return new ResponseEntity<>("Company with pib = " + pib + " is deleted", HttpStatus.OK);
    }
    @GetMapping("/invoices/pib/{company-pib}")
    public ResponseEntity<Object> findInvoicesByCompanyPib(@PathVariable("company-pib") int pib){
        Object details = companyService.findInvoicesByCompanyPib(pib);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }
    @GetMapping("/invoices/unpaid/pib/{company-pib}")
    public ResponseEntity<Object> getUnpaidInvoicesByCompany(@PathVariable("company-pib") int pib){
        Object list = companyService.getUnpaidInvoicesByCompany(pib);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/sum-unpaid/pib/{company-pib}")
    public ResponseEntity<Object> sumUnpaidInvoicesByCompany(@PathVariable("company-pib") int pib){
        Object list = companyService.sumUnpaidInvoicesByCompany(pib);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/invoice-projects/{company-pib}/{invoice-id}")
    public ResponseEntity<Object> findProjectsForCertainCompanyInvoice(
        @PathVariable("company-pib") int pib,
        @PathVariable("invoice-id") Long invoiceId
    ){
        Object list = companyService.findProjectsForCertainCompanyInvoice(pib, invoiceId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
