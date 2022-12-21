package com.vaem.storagemanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "storage_material", schema = "storage_management", catalog = "")
@IdClass(StorageMaterialEntityPK.class)
@Data
public class StorageMaterialEntity {
    
    @Id
    @Column(name = "storage_id")
    private int storageId;

    @Id
    @Column(name = "material_id")
    private int materialId;
    
    @Basic
    @Column(name = "measure_unit")
    private String measureUnit;
    
    @Basic
    @Column(name = "amount")
    private int amount;
    
    @Basic
    @Column(name = "last_operation_date")
    private Timestamp lastOperationDate;
    
    @ManyToOne
    @JoinColumn(name = "storage_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    private StorageEntity storageByStorageId;
    
    @ManyToOne
    @JoinColumn(name = "material_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    private MaterialEntity materialByMaterialId;

}
