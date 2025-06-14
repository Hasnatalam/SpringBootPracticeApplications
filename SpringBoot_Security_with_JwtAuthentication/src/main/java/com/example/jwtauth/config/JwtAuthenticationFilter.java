package com.example.jwtauth.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        
        final String requestURI = request.getRequestURI();
        log.debug("Authenticating request to: {}", requestURI);

        try {
            // 1. Extract Authorization header
            final String authHeader = request.getHeader(AUTH_HEADER);
            if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
                log.debug("No Bearer token found, skipping authentication");
                filterChain.doFilter(request, response);
                return;
            }

            // 2. Extract JWT token
            final String jwt = authHeader.substring(BEARER_PREFIX.length()).trim();
            if (jwt.isEmpty()) {
                log.warn("Empty JWT token provided");
                sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Missing token");
                return;
            }

            // 3. Validate JWT structure
            if (!isJwtStructureValid(jwt)) {
                log.warn("Invalid JWT structure");
                sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid token format");
                return;
            }

            // 4. Extract username
            final String username = jwtService.extractUsername(jwt);
            if (username == null || username.isEmpty()) {
                log.warn("Missing username in JWT claims");
                sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid token claims");
                return;
            }

            // 5. Load user details
            UserDetails userDetails;
            try {
                userDetails = userDetailsService.loadUserByUsername(username);
            } catch (UsernameNotFoundException ex) {
                log.warn("User not found: {}", username);
                sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials");
                return;
            }

            // 6. Validate token
            if (!jwtService.isTokenValid(jwt, userDetails)) {
                log.warn("Invalid JWT token for user: {}", username);
                sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }

            // 7. Set authentication in context
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                log.info("Authenticated user: {}", username);
            }

            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException ex) {
            log.warn("Token expired: {}", ex.getMessage());
            sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Token expired");
        } catch (UnsupportedJwtException ex) {
            log.warn("Unsupported JWT: {}", ex.getMessage());
            sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Unsupported token type");
        } catch (MalformedJwtException ex) {
            log.warn("Malformed JWT: {}", ex.getMessage());
            sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid token format");
        } catch (SignatureException ex) {
            log.warn("Invalid signature: {}", ex.getMessage());
            sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Token verification failed");
        } catch (IllegalArgumentException ex) {
            log.warn("JWT claims empty: {}", ex.getMessage());
            sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid token claims");
        } catch (Exception ex) {
            log.error("Authentication processing failed for {}: {}", requestURI, ex.getMessage(), ex);
            sendError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                "Authentication service unavailable. Please try again later.");
        }
    }

    private boolean isJwtStructureValid(String jwt) {
        // Basic JWT structure validation (3 parts separated by dots)
        String[] parts = jwt.split("\\.");
        return parts.length == 3;
    }

    private void sendError(HttpServletResponse response, int status, String message) 
            throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write(String.format("{\"error\":\"%s\"}", message));
        response.getWriter().flush();
    }
}