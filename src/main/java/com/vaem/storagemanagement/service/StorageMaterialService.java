package com.vaem.storagemanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vaem.storagemanagement.entity.StorageMaterialEntity;
import com.vaem.storagemanagement.entity.StorageMaterialEntityPK;
import com.vaem.storagemanagement.repository.StorageMaterialRepository;

@Service
public class StorageMaterialService {

    private final StorageMaterialRepository storageMaterialRepository;

    public StorageMaterialService(StorageMaterialRepository storageMaterialRepository) {
        this.storageMaterialRepository = storageMaterialRepository;
    }

    public List<StorageMaterialEntity> getAll() {
        return storageMaterialRepository.findAll();
    }

    public Optional<StorageMaterialEntity> getById(StorageMaterialEntityPK id) {
        return storageMaterialRepository.findById(id);
    }

    public List<StorageMaterialEntity> getWithSmallAmount() {
        return storageMaterialRepository.findWhereAmountLessThan10();
    }

    public StorageMaterialEntityPK save(StorageMaterialEntity storageMaterial) {
        storageMaterialRepository.save(storageMaterial);
        return new StorageMaterialEntityPK(
                storageMaterial.getStorageId(), storageMaterial.getMaterialId());
    }

    public void deleteById(StorageMaterialEntityPK id) {
        storageMaterialRepository.deleteById(id);
    }

}
