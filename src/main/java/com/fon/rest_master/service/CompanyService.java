package com.fon.rest_master.service;

import com.fon.rest_master.dto.CompanyDto;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface CompanyService {

    List<CompanyDto> getAll();
    CompanyDto getById(int id) throws EntityNotFoundException;
    CompanyDto save(CompanyDto companyDto);
    CompanyDto update(CompanyDto companyDto) throws EntityNotFoundException;
    void delete(int id) throws EntityNotFoundException;
    Object findInvoicesByCompanyPib(int pib) throws EntityNotFoundException;
    Object getUnpaidInvoicesByCompany(int companyPib) throws EntityNotFoundException;
    Object sumUnpaidInvoicesByCompany(int companyPib) throws EntityNotFoundException;
    Object findProjectsForCertainCompanyInvoice(int pib, Long invoiceId) throws EntityNotFoundException;
}
