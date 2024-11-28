package com.fon.rest_master.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto implements Serializable {

    private int pib;
    @NotEmpty(message = "Company name is mandatory!")
    private String name;
    @NotNull(message = "Company address is mandatory!")
    private String address;
    @NotNull(message = "Company email is mandatory!")
    private String email;
    private String invoices;
}
