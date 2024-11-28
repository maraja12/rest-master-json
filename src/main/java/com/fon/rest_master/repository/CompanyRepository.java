package com.fon.rest_master.repository;

import com.fon.rest_master.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    //find all unpaid invoices by certain company
    @Query(value = "SELECT name, invoices.query('" +
            "<Invoice>" +
            "{/Invoices/Invoice[status=\"UNPAID\"]}" +
            "</Invoice>') as InvoiceDetails " +
            "FROM company " +
            "WHERE name = :name " +
            "AND invoices.exist('/Invoices/Invoice[status=\"UNPAID\"]') = 1",
            nativeQuery = true)
    Object findUnpaidInvoicesByCompany(@Param("name") String companyName);

}
