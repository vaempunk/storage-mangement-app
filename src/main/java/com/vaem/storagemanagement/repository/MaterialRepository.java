package com.vaem.storagemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vaem.storagemanagement.entity.MaterialEntity;

@Repository
public interface MaterialRepository extends JpaRepository<MaterialEntity, Integer> {

    @Query(value = "SELECT *\n" +
    "FROM material m\n" +
    "WHERE m.unit_price > 200\n" +
    "AND NOT EXISTS (\n" +
        "SELECT *\n" +
        "FROM storage st\n" +
        "LEFT JOIN storage_material st_m\n" +
        "ON st.id = st_m.storage_id\n" +
        "WHERE st_m.material_id = m.id AND (st_m.amount IS NULL or st_m.amount <= 100)" +
    ");", nativeQuery = true)
    public List<MaterialEntity> getWithBigPriceAndBigAmount();

}
