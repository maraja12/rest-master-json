package com.fon.rest_master.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto implements Serializable {

    private Long id;
    @NotEmpty(message = "Name of project is mandatory!")
    private String name;
    @NotNull(message = "Budget is mandatory!")
    private BigDecimal budget;
}
