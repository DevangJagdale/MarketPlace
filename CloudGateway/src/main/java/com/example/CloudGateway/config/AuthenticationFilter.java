//package com.example.CloudGateway.config;
//
//
//import com.example.CloudGateway.repositories.TokenRepository;
//import com.example.CloudGateway.services.JwtService;
//import com.example.CloudGateway.services.UserService;
//import io.jsonwebtoken.Claims;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.ReactiveSecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//import lombok.extern.slf4j.Slf4j;
//
//@RefreshScope
//@Component
//@Slf4j
//public class AuthenticationFilter implements GatewayFilter {
//
//    private final RouteValidator routerValidator;
//    private final JwtUtil jwtUtil;
//    private final JwtService jwtService;
//    private final UserService userService;
//    private final TokenRepository tokenRepository;
//
//    @Autowired
//    public AuthenticationFilter(RouteValidator routerValidator, JwtUtil jwtUtil, JwtService jwtService, UserService userService, TokenRepository tokenRepository) {
//        this.routerValidator = routerValidator;
//        this.jwtUtil = jwtUtil;
//        this.jwtService = jwtService;
//        this.userService = userService;
//        this.tokenRepository = tokenRepository;
//    }
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//
//        if (routerValidator.isSecured.test(request)) {
//            if (this.isAuthMissing(request)) {
//                return this.onError(exchange, HttpStatus.UNAUTHORIZED);
//            }
//
//            final String token = this.getAuthHeader(request);
//
//            if (jwtUtil.isInvalid(token)) {
//                return this.onError(exchange, HttpStatus.FORBIDDEN);
//            }
//
//            this.updateRequest(exchange, token);
//        }
//
//        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
//        String jwt = null;
//        String userEmail = null;
//
//        jwt = authHeader.substring(7);
//        log.debug("JWT - {}", jwt);
//        userEmail = jwtService.extractUserName(jwt);
//
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
//
//    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
//        ServerHttpResponse response = exchange.getResponse();
//        response.setStatusCode(httpStatus);
//        return response.setComplete();
//    }
//
//    private String getAuthHeader(ServerHttpRequest request) {
//        return request.getHeaders().getOrEmpty("Authorization").get(0);
//    }
//
//    private boolean isAuthMissing(ServerHttpRequest request) {
//        return !request.getHeaders().containsKey("Authorization");
//    }
//
//    private void updateRequest(ServerWebExchange exchange, String token) {
//        Claims claims = jwtUtil.getAllClaimsFromToken(token);
//        exchange.getRequest().mutate()
//                .header("email", String.valueOf(claims.get("email")))
//                .build();
//    }
//}