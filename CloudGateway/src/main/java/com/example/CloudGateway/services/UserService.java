//package com.example.CloudGateway.services;
//
//import java.time.LocalDateTime;
//import com.example.CloudGateway.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class UserService {
//
//    @Autowired
//    private final UserRepository userRepository;
//
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) {
//                return userRepository.findByEmail(username)
//                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//            }
//        };
//    }
//
//
//}