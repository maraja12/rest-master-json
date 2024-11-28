package com.fon.rest_master.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto implements Serializable {

    private Long id;
    @NotEmpty(message = "Employee name is mandatory!")
    private String name;
    @NotEmpty(message = "Employee surname is mandatory!")
    private String surname;
    @NotNull(message = "Employee birthday is mandatory!")
    private LocalDate birthday;
    @NotNull(message = "Employee address is mandatory!")
    private String address;
    @NotNull(message = "Employee email is mandatory!")
    private String email;

    @NotNull
    private ProfessionDto professionDto;
}
