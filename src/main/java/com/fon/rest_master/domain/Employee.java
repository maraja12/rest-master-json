package com.fon.rest_master.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotEmpty(message = "Employee name is mandatory!")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Employee surname is mandatory!")
    @Column(name = "surname")
    private String surname;
    @NotNull(message = "Employee birthday is mandatory!")
    @Past(message = "Birthday must be a past date.")
    @Column(name = "birthday")
    private LocalDate birthday;
    @NotNull(message = "Employee address is mandatory!")
    @Column(name = "address")
    private String address;
    @NotNull(message = "Employee email is mandatory!")
    @Column(name = "email", unique = true)
    private String email;

    @ManyToOne(optional = false)
    @JoinColumn(name = "profession_id", nullable = false)
    private Profession profession;
}
