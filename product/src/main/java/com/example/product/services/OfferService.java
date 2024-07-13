package com.example.product.services;

import com.example.product.dto.ItemRequest;
import com.example.product.dto.OfferByIdRequest;
import com.example.product.dto.OfferRequest;
import com.example.product.dto.OfferStatusRequest;
import com.example.product.models.Item;
import com.example.product.models.Offer;
import com.example.product.models.OfferStatus;
import com.example.product.repositories.OfferRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferService {
    private final OfferRepository offerRepository;


    public String addOffer(OfferRequest request) {
        var offer = Offer
                .builder()
                .buyerId(request.getBuyerId())
                .sellerId(request.getSellerId())
                .productId(request.getProductId())
                .offerPrice(request.getOfferPrice())
                .description(request.getDescription())
                .status(OfferStatus.WAITING)
                .build();



//        System.out.println("44444444444444444444444444");
        return save(offer);

    }
    public String save(Offer newOffer) {
//        System.out.println("333333333333333333333333333333333333333333");
        if (newOffer.getId() == null) {
            newOffer.setCreatedAt(LocalDateTime.now());
        }

        offerRepository.save(newOffer);

        return "Bid Placed";
    }


    public List<Offer> getOfferByProductId(OfferByIdRequest request){
        System.out.println(request.getId());
        return offerRepository.findByProductId(Integer.valueOf(request.getId()));
    }

    public List<Offer> getOfferByBuyerId(OfferByIdRequest request){
        return offerRepository.findByBuyerId(Integer.valueOf(request.getId()));
    }

    public List<Offer> getOfferBySellerId(OfferByIdRequest request){
        return offerRepository.findBySellerId(Integer.valueOf(request.getId()));
    }


    public String updateOfferStatus(OfferStatusRequest request){
        log.debug(request.getId().toString());
        Optional<Offer> offer=offerRepository.findById(request.getId());
        if(request.getStatus().equals("ACCEPTED"))offer.get().setStatus(OfferStatus.ACCEPTED);
        else offer.get().setStatus(OfferStatus.REJECTED);
        Offer convertedOffer=Offer
                .builder()
                .id(offer.get().getId())
                .buyerId(offer.get().getBuyerId())
                .sellerId(offer.get().getSellerId())
                .productId(offer.get().getProductId())
                .offerPrice(offer.get().getOfferPrice())
                .description(offer.get().getDescription())
                .status(offer.get().getStatus())
                .build();
        save(convertedOffer);
        return "Offer updated";
    }

    public String deleteOffer(OfferByIdRequest request){
        offerRepository.deleteById(Integer.valueOf(request.getId()));
        return "Offer deleted";
    }
}
