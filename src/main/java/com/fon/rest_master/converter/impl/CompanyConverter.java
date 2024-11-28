package com.fon.rest_master.converter.impl;

import com.fon.rest_master.converter.DtoEntityConverter;
import com.fon.rest_master.domain.Company;
import com.fon.rest_master.dto.CompanyDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyConverter implements DtoEntityConverter<CompanyDto, Company> {
    @Override
    public CompanyDto toDto(Company company) {
        return CompanyDto.builder()
                .pib(company.getPib())
                .name(company.getName())
                .address(company.getAddress())
                .email(company.getEmail())
                .invoices(company.getInvoices())
                .build();
    }

    @Override
    public Company toEntity(CompanyDto companyDto) {
        return Company.builder()
                .pib(companyDto.getPib())
                .name(companyDto.getName())
                .address(companyDto.getAddress())
                .email(companyDto.getEmail())
                .invoices(companyDto.getInvoices())
                .build();
    }
}
