package com.netshop.gateway_service.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

    private static final String SECRET = "claveSuperSecretaParaGenerarTokensSegurosDeNetShop123456";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public JwtAuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // 1️⃣ Obtener el header Authorization
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            // 2️⃣ Si no hay token, devolver error 401
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return this.onError(exchange, "Token faltante o inválido", HttpStatus.UNAUTHORIZED);
            }

            // 3️⃣ Extraer el token
            String token = authHeader.substring(7);

            try {
                // 4️⃣ Validar el token
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                System.out.println("✅ Token válido para usuario: " + claims.getSubject());

            } catch (Exception e) {
                return this.onError(exchange, "Token no válido: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
            }

            // 5️⃣ Si todo OK → continuar la petición
            return chain.filter(exchange);
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus status) {
        exchange.getResponse().setStatusCode(status);
        return exchange.getResponse().setComplete();
    }

    public static class Config {
        // Clase vacía (necesaria para el filtro)
    }
}
