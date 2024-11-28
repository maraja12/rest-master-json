package com.fon.rest_master.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "engagement")
public class Engagement {

    @EmbeddedId
    @NotNull
    private EngagementId id;

    @NotNull(message = "Employee role is mandatory!")
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @NotNull(message = "Number of hours is mandatory!")
    @Column(name = "num_of_hours")
    private int numOfHours;
}
