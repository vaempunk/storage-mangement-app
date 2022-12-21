package com.vaem.storagemanagement.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StorageMaterialDTO {
    
    private String materialName;

    private int storageId;
    
    private int amount;

}
