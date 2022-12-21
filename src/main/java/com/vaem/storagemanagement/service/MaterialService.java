package com.vaem.storagemanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vaem.storagemanagement.entity.MaterialEntity;
import com.vaem.storagemanagement.repository.MaterialRepository;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<MaterialEntity> getAll() {
        return materialRepository.findAll();
    }

    public Optional<MaterialEntity> getById(int id) {
        return materialRepository.findById(id);
    }

    public List<MaterialEntity> getWithBigPriceAndBigAmount() {
        return materialRepository.getWithBigPriceAndBigAmount();
    }

    public int save(MaterialEntity material) {
        materialRepository.save(material);
        return material.getId();
    }

    public void deleteById(int id) {
        materialRepository.deleteById(id);
    }
    
}
