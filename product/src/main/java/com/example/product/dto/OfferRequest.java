package com.example.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferRequest {

    Integer productId;
    Integer sellerId;
    Integer buyerId;
    Double offerPrice;
    String description;
}
