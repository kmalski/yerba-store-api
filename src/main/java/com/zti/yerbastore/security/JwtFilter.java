package com.zti.yerbastore.security;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Component
public class JwtFilter extends BasicAuthenticationFilter {

    public JwtFilter(AuthenticationManager manager) {
        super(manager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            SecurityContextHolder.getContext().setAuthentication(null);
        } else {
            UsernamePasswordAuthenticationToken authResult = authenticateByToken(header)
                    .orElseGet(() -> {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        return null;
                    });

            SecurityContextHolder.getContext().setAuthentication(authResult);
        }

        chain.doFilter(request, response);
    }

    private Optional<UsernamePasswordAuthenticationToken> authenticateByToken(String header) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(SecretKeyGenerator.getSecretKeyBytes())
                    .parseClaimsJws(header.replace("Bearer ", ""));

            String email = claims.getBody().get("email").toString();
            String role = claims.getBody().get("role").toString();

            Set<SimpleGrantedAuthority> authoritySet = Collections.singleton(new SimpleGrantedAuthority(role));

            return Optional.of(new UsernamePasswordAuthenticationToken(email, null, authoritySet));
        } catch (ExpiredJwtException | SignatureException exception) {
            return Optional.empty();
        }
    }

}
