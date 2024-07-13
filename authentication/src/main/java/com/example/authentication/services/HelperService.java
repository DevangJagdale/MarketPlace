package com.example.authentication.services;

import com.example.authentication.dto.*;
import org.springframework.stereotype.Service;

import com.example.authentication.models.User;
import com.example.authentication.repositories.TokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HelperService {

    private final TokenRepository tokenRepository;

    public UserDetailResponse getDetails(HomeRequest request){
        User user = tokenRepository.findByToken(request.getToken()).get().getUser();
//        System.out.println(user.getFirstName());
        return UserDetailResponse.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

}
