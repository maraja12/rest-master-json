package com.fon.rest_master.controller;

import com.fon.rest_master.dto.ProfessionDto;
import com.fon.rest_master.service.ProfessionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profession")
public class ProfessionController {

    private ProfessionService professionService;

    public ProfessionController(ProfessionService professionService) {
        this.professionService = professionService;
    }

    @GetMapping
    public ResponseEntity<List<ProfessionDto>> getAll(){
        List<ProfessionDto> professions = professionService.getAll();
        return new ResponseEntity<>(professions, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionDto> findById(@PathVariable Long id){
        ProfessionDto profession = professionService.getById(id);
        return new ResponseEntity<>(profession, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ProfessionDto> save(@RequestBody @Valid ProfessionDto profession){
        ProfessionDto professionSaved = professionService.save(profession);
        return new ResponseEntity<>(professionSaved, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<ProfessionDto> update(@RequestBody @Valid ProfessionDto profession){
        ProfessionDto professionUpdated = professionService.update(profession);
        return new ResponseEntity<>(professionUpdated, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        professionService.delete(id);
        return new ResponseEntity<>("Profession with id = " + id + " is deleted", HttpStatus.OK);
    }
}
