package com.fon.rest_master.service;

import com.fon.rest_master.domain.Employee;
import com.fon.rest_master.domain.EngagementId;
import com.fon.rest_master.domain.Month;
import com.fon.rest_master.domain.Project;
import com.fon.rest_master.dto.EngagementDto;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface EngagementService {

    List<EngagementDto> getAll();
    EngagementDto getById(Long projectId, Long employeeId, Month month, int year)
            throws EntityNotFoundException;
    EngagementDto save(EngagementDto engagementDto);
    EngagementDto update(EngagementDto engagementDto) throws EntityNotFoundException;
    void delete(Long projectId, Long employeeId, Month month, int year)
            throws EntityNotFoundException;
}
