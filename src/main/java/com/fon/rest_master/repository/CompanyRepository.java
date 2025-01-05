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
            "FROM OPENJSON(invoices, '$.Invoices') " +
            "WHERE JSON_VALUE(value, '$.status') = 'UNPAID') AS unpaid_invoices " +
            "FROM Company " +
            "WHERE pib = :pib",
            nativeQuery = true)
    Object findUnpaidInvoicesByCompany(@Param("pib") int pib);

    //  sum all unpaid invoices for particular company and show company name, pib and email
    @Query(value = "SELECT name, pib, email, " +
            "(SELECT SUM(CAST(JSON_VALUE(items.value, '$.price_per_hour') AS DECIMAL(10, 2))) " +
            "FROM OPENJSON(invoices, '$.Invoices') AS invoices_array " +
            "CROSS APPLY OPENJSON(invoices_array.value, '$.InvoiceItems') AS items " +
            "WHERE JSON_VALUE(invoices_array.value, '$.status') = 'UNPAID') AS total_price " +
            "FROM Company " +
            "WHERE pib = :pib " +
            "GROUP BY name, pib, email, invoices",
            nativeQuery = true)
    Object sumUnpaidInvoicesByCompany(@Param("pib") int pib);

    //find name of projects for certain company invoice
    @Query(value =
            "SELECT c.name AS company_name, " +
            "STRING_AGG(p.name, ', ') AS project_names " +
            "FROM company c " +
            "CROSS APPLY OPENJSON(c.invoices, '$.Invoices') AS invoices_array " +
            "CROSS APPLY OPENJSON(invoices_array.value, '$.InvoiceItems') AS items " +
            "INNER JOIN project p ON " +
            "CAST(JSON_VALUE(items.value, '$.project_id') AS BIGINT) = p.id " +
            "WHERE JSON_VALUE(invoices_array.value, '$.id') = :invoice_id " +
            "AND c.pib = :pib " +
            "GROUP BY c.name;",
            nativeQuery = true
    )
    Object findProjectsForCertainCompanyInvoice(@Param("pib") int pib, @Param("invoice_id") Long invoiceId);

    //find employees id, name and surname for certain company invoice
    @Query(value =
            "SELECT c.name AS company_name, " +
            "STRING_AGG(concat('id: ', e.id, ' ', e.name, ' ', e.surname), ', ') AS employees " +
            "FROM company c " +
            "CROSS APPLY OPENJSON(c.invoices, '$.Invoices') AS invoices_array " +
            "CROSS APPLY OPENJSON(invoices_array.value, '$.InvoiceItems') AS items " +
            "INNER JOIN employee e ON " +
            "CAST(JSON_VALUE(items.value, '$.employee_id') AS BIGINT) = e.id " +
            "WHERE JSON_VALUE(invoices_array.value, '$.id') = :invoice_id " +
            "AND c.pib = :pib " +
            "GROUP BY c.name;",
            nativeQuery = true)
    Object findEmployeesForCertainCompanyInvoice(@Param("pib") int pib, @Param("invoice_id") Long invoiceId);

    //find role of employee on project for certain company invoice (invoice item)
    @Query(value =
            "SELECT c.name AS company_name, " +
            "e.project_id as project_id, " +
            "e.employee_id as employee_id, " +
            "e.role AS employee_role " +
            "FROM company c " +
            "CROSS APPLY OPENJSON(c.invoices, '$.Invoices') AS invoices_array " +
            "CROSS APPLY OPENJSON(invoices_array.value, '$.InvoiceItems') AS items " +
            "INNER JOIN engagement e ON " +
            "CAST(JSON_VALUE(items.value, '$.project_id') AS BIGINT) = e.project_id " +
            "AND CAST(JSON_VALUE(items.value, '$.employee_id') AS BIGINT) = e.employee_id " +
            "WHERE JSON_VALUE(invoices_array.value, '$.id') = :invoice_id " +
            "AND JSON_VALUE(items.value, '$.seq_num') = :seq_num " +
            "AND c.pib = :pib;",
            nativeQuery = true)
    Object findEmployeeRoleOnProjectForCompanyInvoiceItem(
            @Param("pib") int pib, @Param("invoice_id") Long invoiceId, @Param("seq_num") int seqNum);
}
