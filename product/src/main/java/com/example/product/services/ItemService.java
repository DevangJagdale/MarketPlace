package com.example.product.services;

import com.example.product.dto.ItemByIdRequest;
import com.example.product.dto.ItemRequest;
import com.example.product.dto.ItemUpdateRequest;
import com.example.product.repositories.ItemRepository;
import com.example.product.models.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;
    public String addItem(ItemRequest request) {
        var item = Item
                .builder()
                .itemName(request.getItemName())
                .sellerId(request.getSellerId())
                .expectedPrice(request.getExpectedPrice())
                .itemLocation(request.getItemLocation())
                .itemDescription(request.getItemDescription())
                .build();


        save(item);
        return "Item added";

    }

    public List<Item> getItem(){
        var items= itemRepository.findAll();

        return  items;
    }

    public Optional<Item> getItemById(ItemByIdRequest request){
        var items= itemRepository.findById(Integer.valueOf(request.getId()));

        return  items;
    }


    public List<Item> getItemByUserId(ItemByIdRequest request){
        var items= itemRepository.findBySellerId(request.getId());

        return  items;
    }

    public String deleteItem(ItemByIdRequest request){
        itemRepository.deleteById(Integer.valueOf(request.getId()));
        return "Item deleted";
    }

    public String updateItem(ItemUpdateRequest request){
//        log.debug(request.getId().toString());
//        Optional<Item> itemById=itemRepository.findById(request.getId());
        var item= Item
                .builder()
                .id(request.getId())
                .itemName(request.getItemName())
                .sellerId(request.getSellerId())
                .expectedPrice(request.getExpectedPrice())
                .itemLocation(request.getItemLocation())
                .itemDescription(request.getItemDescription())
                .createdAt(LocalDateTime.now())
                .build();
        itemRepository.save(item);
        return "Item updated";
    }
    public Item save(Item newItem) {
        if (newItem.getId() == null) {
            newItem.setCreatedAt(LocalDateTime.now());
        }

        return itemRepository.save(newItem);
    }

}
