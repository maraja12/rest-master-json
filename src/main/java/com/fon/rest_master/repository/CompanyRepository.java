package com.fon.rest_master.repository;

import com.fon.rest_master.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

//    find all invoices for particular company and show company details like name, pib and email
    @Query(value = "SELECT name, pib, email, invoices " +
            "FROM Company " +
            "WHERE pib = :pib ",
            nativeQuery = true)
    Object findInvoicesByCompanyPib(@Param("pib") int companyPib);

//    find all unpaid invoices for particular company and show company details like name, pib and email
    @Query(value = "SELECT name, pib, email, " +
            "(SELECT STRING_AGG(JSON_QUERY(value), ',') " +
            "FROM OPENJSON(invoices) " +
            "WHERE JSON_VALUE(value, '$.status') = 'UNPAID') AS unpaid_invoices " +
            "FROM Company " +
            "WHERE pib = :pib",
            nativeQuery = true)
    Object findUnpaidInvoicesByCompany(@Param("pib") int pib);

}
