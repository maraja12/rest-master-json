package com.fon.rest_master.service.impl;

import com.fon.rest_master.converter.impl.ProfessionConverter;
import com.fon.rest_master.domain.Profession;
import com.fon.rest_master.dto.ProfessionDto;
import com.fon.rest_master.exception.EntityAlreadyExistsException;
import com.fon.rest_master.repository.ProfessionRepository;
import com.fon.rest_master.service.ProfessionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessionServiceImpl implements ProfessionService {

    private ProfessionRepository professionRepository;
    private ProfessionConverter professionConverter;

    public ProfessionServiceImpl(ProfessionRepository professionRepository, ProfessionConverter professionConverter) {
        this.professionRepository = professionRepository;
        this.professionConverter = professionConverter;
    }

    @Override
    public List<ProfessionDto> getAll() {
        return professionRepository.findAll().stream()
                .map(entity -> professionConverter.toDto(entity)).collect(Collectors.toList());
    }

    @Override
    public ProfessionDto getById(Long id) throws EntityNotFoundException {
        Optional<Profession> professionOpt = professionRepository.findById(id);
        if(professionOpt.isPresent()) {
            Profession profession = professionOpt.get();
            return professionConverter.toDto(profession);
        }
        else{
            throw new EntityNotFoundException("Profession with id = " + id + " is not found");
        }
    }

    @Override
    public ProfessionDto save(ProfessionDto professionDto) throws EntityAlreadyExistsException {
        Optional<Profession> professionOpt = professionRepository.findByName(professionDto.getName());
        if(professionOpt.isPresent()) {
            throw new EntityAlreadyExistsException("Proffesion with name = " + professionDto.getName() + " already exists");
        }
        else{
            Profession profession = professionConverter.toEntity(professionDto);
            profession = professionRepository.save(profession);
            return professionConverter.toDto(profession);
        }
    }

    @Override
    public ProfessionDto update(ProfessionDto professionDto) throws EntityNotFoundException {
        Optional<Profession> professionOpt = professionRepository.findById(professionDto.getId());
        if(professionOpt.isPresent()) {
            Profession profession = professionOpt.get();
            profession.setName(professionDto.getName());
            profession = professionRepository.save(profession);
            return professionConverter.toDto(profession);
        }
        else{
            throw new EntityNotFoundException("Profession with id = " + professionDto.getId() + " is not found");
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Optional<Profession> professionOpt = professionRepository.findById(id);
        if(professionOpt.isPresent()) {
            Profession profession = professionOpt.get();
            professionRepository.delete(profession);
        }
        else{
            throw new EntityNotFoundException("Profession with id = " + id + " is not found");
        }
    }
}
