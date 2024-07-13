package com.example.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDetailResponse {
    Integer sellerId;
    String itemName;
    String itemLocation;
    String itemDescription;
    Double expectedPrice;
    LocalDateTime createdAt;
}
