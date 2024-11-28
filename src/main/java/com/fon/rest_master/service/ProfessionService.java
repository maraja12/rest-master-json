package com.fon.rest_master.service;

import com.fon.rest_master.dto.ProfessionDto;
import com.fon.rest_master.exception.EntityAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface ProfessionService {

    List<ProfessionDto> getAll();
    ProfessionDto getById(Long id) throws EntityNotFoundException;
    ProfessionDto save(ProfessionDto professionDto) throws EntityAlreadyExistsException;
    ProfessionDto update(ProfessionDto professionDto) throws EntityNotFoundException;
    void delete(Long id) throws EntityNotFoundException;
}
