package com.vaem.storagemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaem.storagemanagement.entity.StorageEntity;

@Repository
public interface StorageRepository extends JpaRepository<StorageEntity, Integer> {
    
}
