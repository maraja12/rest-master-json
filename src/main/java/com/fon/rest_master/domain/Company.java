package com.fon.rest_master.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company")
public class Company {

    @Id
    @Column(name = "pib")
    private int pib;
    @NotEmpty(message = "Company name is mandatory!")
    @Column(name = "name")
    private String name;
    @NotNull(message = "Company address is mandatory!")
    @Column(name = "address")
    private String address;
    @NotNull(message = "Company email is mandatory!")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "invoices", columnDefinition = "xml")
    private String invoices;
}
