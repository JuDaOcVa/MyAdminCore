package com.judaocva.myadmincore.controllers;

import com.judaocva.myadmincore.models.dtos.GenericResponseDto;
import com.judaocva.myadmincore.services.generic.GenericService;
import org.springframework.web.bind.annotation.*;

public abstract class GenericController<R, ID> {

    private final GenericService<R, ID> service;

    protected GenericController(GenericService<R, ID> service) {
        this.service = service;
    }

    @PostMapping("/save")
    public GenericResponseDto save(@RequestBody R request) {
        return service.save(request);
    }

    @PutMapping("/update/{id}")
    public GenericResponseDto update(@PathVariable ID id, @RequestBody R request) {
        return service.update(id, request);
    }

    @GetMapping("/show/{id}")
    public GenericResponseDto getById(@PathVariable ID id) {
        return service.findById(id);
    }

    @GetMapping("/show/all")
    public GenericResponseDto getAll() {
        return service.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public GenericResponseDto deleteById(@PathVariable ID id) {
        return service.deleteById(id);
    }
}