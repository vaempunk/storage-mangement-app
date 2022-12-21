package com.vaem.storagemanagement.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MaterialWithStorageAmountDTO {
    
    private String name;

    private int storageAmount;

}
