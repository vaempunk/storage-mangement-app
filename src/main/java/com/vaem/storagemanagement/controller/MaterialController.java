package com.vaem.storagemanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vaem.storagemanagement.dto.MaterialWithStorageAmountDTO;
import com.vaem.storagemanagement.entity.MaterialEntity;
import com.vaem.storagemanagement.exception.MaterialNotFoundException;
import com.vaem.storagemanagement.service.MaterialService;

@RestController
@RequestMapping("/materials")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public List<MaterialEntity> getAll() {
        return materialService.getAll();
    }

    @GetMapping("/{id}")
    public MaterialEntity getById(@PathVariable("id") int id) {
        return materialService
                .getById(id)
                .orElseThrow(() -> new MaterialNotFoundException());
    }

    @GetMapping("/big-amount")
    public List<MaterialWithStorageAmountDTO> getWithBigAmount() {

        return materialService.getAll()
                .stream()
                .map((entity) -> MaterialWithStorageAmountDTO
                        .builder()
                        .name(entity.getName())
                        .storageAmount(getStoragesAmount(entity))
                        .build())
                .toList();

    }

    @GetMapping("big-price-big-amount")
    public List<MaterialEntity> getWithBigPriceAndBigAmount() {
        return materialService.getWithBigPriceAndBigAmount();
    }

    // amount of storages where amount of the material > 100
    private int getStoragesAmount(MaterialEntity entity) {
        return (int) entity.getStorageMaterialsById()
                .stream()
                .filter((storageMaterial) -> storageMaterial.getAmount() > 100)
                .count();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int putNew(@RequestBody MaterialEntity material) {
        return materialService.save(material);
    }

    @PutMapping("/{id}")
    public void put(
            @PathVariable("id") int id,
            @RequestBody MaterialEntity material) {
        material.setId(id);
        materialService.save(material);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        materialService.deleteById(id);
    }

}
