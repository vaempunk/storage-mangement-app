package com.vaem.storagemanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vaem.storagemanagement.entity.StorageEntity;
import com.vaem.storagemanagement.repository.StorageRepository;

@Service
public class StorageService {
    
    private final StorageRepository storageRepository;

    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public List<StorageEntity> getAll() {
        return storageRepository.findAll();
    }

    public Optional<StorageEntity> getById(int id) {
        return storageRepository.findById(id);
    }

    public int save(StorageEntity storage) {
        storageRepository.save(storage);
        return storage.getId();
    }

    public void deleteById(int id) {
        storageRepository.deleteById(id);
    }

}
