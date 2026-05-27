package com.codingsrv.api_gateway.filters;

import com.codingsrv.api_gateway.service.JwtService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthenticationGatewayFilterFactory.Config> {

    private final JwtService jwtService;

    public AuthenticationGatewayFilterFactory(JwtService jwtService) {
        super(Config.class);
        this.jwtService = jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            if (!config.enabled) return chain.filter(exchange);

            String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (authorizationHeader == null){
                // In response, we can set the status code as well
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete(); // this will set response completely
            }
            // extract JWT token from Authorization header
            String token = authorizationHeader.split("Bearer ")[1];

            // extract userId from JWT token
            Long userId = jwtService.getUserIdFromToken(token);

            // mutate the incoming request and add custom header
            var mutatedRequest = exchange.getRequest()
                    .mutate()
                    .header("X-User-Id", userId.toString())
                    .build();

            // mutate the exchange object with modified request
            var mutatedExchange = exchange.mutate()
                    .request(mutatedRequest)
                    .build();

            // forward modified request to next filter or target microservice
            return chain.filter(mutatedExchange);
        };
    }

    @Data
    public static class Config {
        private boolean enabled;
    }


}
