package com.vaem.storagemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vaem.storagemanagement.entity.StorageMaterialEntity;
import com.vaem.storagemanagement.entity.StorageMaterialEntityPK;

@Repository
public interface StorageMaterialRepository extends JpaRepository<StorageMaterialEntity, StorageMaterialEntityPK> {
    
    // Задание 1: Сведения о наличии материалов в малом (<10) количестве:
    // наименование материала; номер склада; количество
    @Query(value = "SELECT * FROM storage_material st_m WHERE st_m.amount < 10;", nativeQuery = true)
    List<StorageMaterialEntity> findWhereAmountLessThan10();

}