package com.vaem.storagemanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vaem.storagemanagement.entity.StorageEntity;
import com.vaem.storagemanagement.exception.StorageNotFoundException;
import com.vaem.storagemanagement.service.StorageService;

@RestController
@RequestMapping("/storages")
public class StorageController {

    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public java.util.List<StorageEntity> getAll() {
        return storageService.getAll();
    }

    @GetMapping("/{id}")
    public StorageEntity getById(@PathVariable("id") int id) {
        return storageService
                .getById(id)
                .orElseThrow(() -> new StorageNotFoundException());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int putNew(@RequestBody StorageEntity storage) {
        return storageService.save(storage);
    }

    @PutMapping("/{id}")
    public void put(
            @PathVariable("id") int id,
            @RequestBody StorageEntity storage) {
                System.out.println(id);
        storage.setId(id);
        storageService.save(storage);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        storageService.deleteById(id);
    }

}
