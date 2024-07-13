package com.example.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest {
    Integer sellerId;
    String itemName;
    String itemLocation;
    String itemDescription;
    Double expectedPrice;
}