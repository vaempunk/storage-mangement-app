package com.vaem.storagemanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "material", schema = "storage_management", catalog = "")
@Data
public class MaterialEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;
    
    @Basic
    @Column(name = "measure_unit")
    private String measureUnit;
    
    @Basic
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    
    @OneToMany(mappedBy = "materialByMaterialId")
    @JsonIgnore
    private Collection<StorageMaterialEntity> storageMaterialsById;

}
