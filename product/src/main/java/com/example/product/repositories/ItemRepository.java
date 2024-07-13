package com.example.product.repositories;

import com.example.product.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    Optional<Item> findById(Integer id);

    @Query
    (value = """
            select i from Item i\s
            """)
    List<Item> findAll();

    List<Item> findBySellerId(Integer id);

    void deleteById(Integer id);

}
