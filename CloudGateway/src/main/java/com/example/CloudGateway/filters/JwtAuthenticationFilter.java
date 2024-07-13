//package com.example.CloudGateway.filters;
//
//import com.example.CloudGateway.repositories.TokenRepository;
//import com.example.CloudGateway.services.JwtService;
//import com.example.CloudGateway.services.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.context.ReactiveSecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//import reactor.util.annotation.NonNullApi;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class JwtAuthenticationFilter implements WebFilter {
//
//    private final JwtService jwtService;
//    private final UserService userService;
//    private final TokenRepository tokenRepository;
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
//        String jwt = null;
//        String userEmail = null;
//
//        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
//            return chain.filter(exchange);
//        }
//
//        jwt = authHeader.substring(7);
//        log.debug("JWT - {}", jwt);
//        userEmail = jwtService.extractUserName(jwt);
//
//        if (StringUtils.isNotEmpty(userEmail) && ReactiveSecurityContextHolder.getContext().block().getAuthentication() == null) {
//            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
//            boolean isTokenValid = tokenRepository.findByToken(jwt)
//                    .map(t -> !t.isExpired() && !t.isRevoked())
//                    .orElse(false);
//
//            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
//                log.debug("User - {}", userDetails.getUsername());
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//
//                return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authToken));
//            }
//        }
//
//        return chain.filter(exchange);
//    }
//}
