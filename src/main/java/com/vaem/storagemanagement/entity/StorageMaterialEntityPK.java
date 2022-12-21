package com.vaem.storagemanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageMaterialEntityPK implements Serializable {

    @Column(name = "storage_id")
    @Id
    private int storageId;

    @Column(name = "material_id")
    @Id
    private int materialId;

}
