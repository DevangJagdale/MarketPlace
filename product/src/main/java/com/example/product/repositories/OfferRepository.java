package com.example.product.repositories;

import com.example.product.models.Offer;
import com.example.product.models.OfferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
    Optional<Offer> findById(Integer id);
    List<Offer> findByProductId(Integer id);

    List<Offer> findBySellerId(Integer id);
    List<Offer> findByBuyerId(Integer id);

    @Modifying
    @Query
            ("""
            update Offer set status=:newStatus where id = :id\s
            """)
    void updateOfferStatus(@Param(value = "newStatus") OfferStatus newStatus, @Param(value = "id") Integer offerId);

    void deleteById(Integer id);
}
