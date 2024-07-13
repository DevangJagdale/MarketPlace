package com.example.product.controllers;

import com.example.product.dto.*;
import com.example.product.models.Item;
import com.example.product.models.Offer;
import com.example.product.models.OfferStatus;
import com.example.product.services.ItemService;
import com.example.product.services.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private  final OfferService offerService;

    @PostMapping("/addItem")
    public String addItem(@RequestBody ItemRequest request){
        return itemService.addItem(request);
    }

    @GetMapping("/getItem")
    public List<Item> getItem(){
        return itemService.getItem();
    }

    @GetMapping("/getItemById")
    public Optional<Item> getItemById(@RequestBody ItemByIdRequest request){
        return itemService.getItemById(request);
    }

    @GetMapping("/getItemByUserId")
    public List<Item> getItemByUserId(@RequestBody ItemByIdRequest request){
        return itemService.getItemByUserId(request);
    }

    @PutMapping("/updateItem")
    public String updateItem(@RequestBody ItemUpdateRequest request){
        log.debug(request.getId().toString());
        return itemService.updateItem(request);
    }

    @DeleteMapping("/deleteItem")
    public String deleteItem(@RequestBody ItemByIdRequest request){
//        return ItemService.deleteItem(request);
        return "Item deleted";
    }

    @PostMapping("/addOffer")
    public String addOffer(@RequestBody OfferRequest request){
        return offerService.addOffer(request);
    }

    @GetMapping("/getOfferByProductId")
    public List<Offer> getOfferByProductId(@RequestBody OfferByIdRequest request){
        return offerService.getOfferByProductId(request);
    }

    @GetMapping("/getOfferByBuyerId")
    public List<Offer> getOfferByBuyerId(@RequestBody OfferByIdRequest request){
        return offerService.getOfferByBuyerId(request);
    }

    @GetMapping("/getOfferBySellerId")
    public List<Offer> getOfferBySellerId(@RequestBody OfferByIdRequest request){
        return offerService.getOfferBySellerId(request);
    }

    @PutMapping("/updateOfferStatus")
    public String updateOffer(@RequestBody OfferStatusRequest request){
        return offerService.updateOfferStatus(request);
    }

    @DeleteMapping("/deleteOffer")
    public String deleteOffer(@RequestBody OfferByIdRequest request){
        return offerService.deleteOffer(request);
    }


}
