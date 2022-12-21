package com.vaem.storagemanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "storage", schema = "storage_management", catalog = "")
@Data
public class StorageEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "owner_surname")
    private String ownerSurname;
    
    @OneToMany(mappedBy = "storageByStorageId")
    @JsonIgnore
    private Collection<StorageMaterialEntity> storageMaterialsById;

}
