package com.example.authentication.controllers;


import com.example.authentication.dto.*;
import com.example.authentication.services.AuthenticationService;
import org.springframework.web.bind.annotation.*;

import com.example.authentication.services.HelperService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HelperController {

    private final HelperService helperService;

    @PostMapping("/profile")
    public UserDetailResponse profile(@RequestBody HomeRequest request){
        return helperService.getDetails(request);
    }

}
