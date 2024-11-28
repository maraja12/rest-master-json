package com.fon.rest_master.controller;

import com.fon.rest_master.domain.EngagementId;
import com.fon.rest_master.domain.Month;
import com.fon.rest_master.dto.EngagementDto;
import com.fon.rest_master.service.EngagementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/engagement")
public class EngagementController {

    private EngagementService engagementService;

    public EngagementController(EngagementService engagementService) {
        this.engagementService = engagementService;
    }

    @GetMapping
    public ResponseEntity<List<EngagementDto>> getAll() {
        List<EngagementDto> engagementDtos = engagementService.getAll();
        return new ResponseEntity<>(engagementDtos, HttpStatus.OK);
    }
    @GetMapping("/{project-id}/{employee-id}/{month}/{year}")
    public ResponseEntity<EngagementDto> getById(
            @PathVariable("project-id") Long projectId,
            @PathVariable("employee-id") Long employeeId,
            @PathVariable Month month,
            @PathVariable int year
            ) {
        EngagementDto engagementDto =
                engagementService.getById(projectId, employeeId, month, year);
        return new ResponseEntity<>(engagementDto, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<EngagementDto> save(@Valid @RequestBody EngagementDto engagementDto) {
        EngagementDto engagement = engagementService.save(engagementDto);
        return new ResponseEntity<>(engagement, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<EngagementDto> update(@Valid @RequestBody EngagementDto engagementDto) {
        EngagementDto engagement = engagementService.update(engagementDto);
        return new ResponseEntity<>(engagement, HttpStatus.OK);
    }
    @DeleteMapping("/{project-id}/{employee-id}/{month}/{year}")
    public ResponseEntity<String> delete(
            @PathVariable("project-id") Long projectId,
            @PathVariable("employee-id") Long employeeId,
            @PathVariable Month month,
            @PathVariable int year) {
        engagementService.delete(projectId, employeeId, month, year);
        return new ResponseEntity<>("Engagement is deleted!", HttpStatus.OK);
    }

}
