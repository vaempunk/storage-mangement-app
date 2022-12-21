package com.vaem.storagemanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vaem.storagemanagement.dto.StorageMaterialDTO;
import com.vaem.storagemanagement.entity.StorageMaterialEntity;
import com.vaem.storagemanagement.entity.StorageMaterialEntityPK;
import com.vaem.storagemanagement.exception.StorageMaterialNotFoundException;
import com.vaem.storagemanagement.service.StorageMaterialService;

@RestController
public class StorageMaterialController {

    private final StorageMaterialService storageMaterialService;

    public StorageMaterialController(StorageMaterialService storageMaterialService) {
        this.storageMaterialService = storageMaterialService;
    }

    @GetMapping("/storages-materials")
    public List<StorageMaterialEntity> getAll() {
        return storageMaterialService.getAll();
    }

    @GetMapping("/storages/{storageId}/materials/{materialId}/storages-materials")
    public StorageMaterialEntity getByStorageIdAndMaterialId(
            @PathVariable("storageId") int storageId,
            @PathVariable("materialId") int materialId) {

        return storageMaterialService
                .getById(new StorageMaterialEntityPK(storageId, materialId))
                .orElseThrow(() -> new StorageMaterialNotFoundException());

    }

    @GetMapping("/storages-materials/small-amount")
    public List<StorageMaterialDTO> getWithSmallAmount() {
        return storageMaterialService.getWithSmallAmount()
                .stream()
                .map((entity) -> StorageMaterialDTO
                        .builder()
                        .materialName(entity.getMaterialByMaterialId().getName())
                        .storageId(entity.getStorageId())
                        .amount(entity.getAmount())
                        .build())
                .toList();

    }

    @PutMapping("/storages-materials")
    @ResponseStatus(HttpStatus.CREATED)
    public StorageMaterialEntityPK put(@RequestBody StorageMaterialEntity storageMaterial) {
        return storageMaterialService.save(storageMaterial);
    }

    @DeleteMapping("/storages/{storageId}/materials/{materialId}/storages-materials")
    public void deleteByStorageIdAndMaterialId(
            @PathVariable("storageId") int storageId,
            @PathVariable("materialId") int materialId) {
        storageMaterialService.deleteById(
                new StorageMaterialEntityPK(storageId, materialId));
    }

}
