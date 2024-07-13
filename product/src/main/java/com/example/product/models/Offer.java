package com.example.product.models;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer productId;
    Integer sellerId;
    Integer buyerId;
    Double offerPrice;
    @Enumerated(EnumType.STRING)
    OfferStatus status;

    String description;
    LocalDateTime createdAt;

}
