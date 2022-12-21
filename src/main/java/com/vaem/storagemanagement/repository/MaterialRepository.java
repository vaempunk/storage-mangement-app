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
            "AND (SELECT COUNT(*) FROM storage) = (\n" +
            "SELECT COUNT(*)\n" +
            "FROM storage_material st_m\n" +
            "WHERE st_m.material_id = m.id\n" +
            ")\n" +
            "AND 100 < ALL(\n" +
            "SELECT st_m.amount\n" +
            "FROM storage_material st_m\n" +
            "WHERE st_m.material_id = m.id\n" +
            ");", nativeQuery = true)
    public List<MaterialEntity> getWithBigPriceAndBigAmount();

}
