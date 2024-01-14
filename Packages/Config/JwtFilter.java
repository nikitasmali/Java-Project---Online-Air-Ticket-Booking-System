package com.airline.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String authHeader = request.getHeader("Authorization");

        // Check if the request is an OPTIONS request (preflight request)
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
        } else {
            // Verify the presence and format of the "Authorization" header
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                // Respond with an error when the header is missing or invalid
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or Invalid Authorization Header");
                return;
            }

            // Extract and verify the JWT token
            final String token = authHeader.substring(7);

            try {
                final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
                // Set the JWT claims in the request for further processing
                request.setAttribute("claims", claims);
            } catch (final SignatureException e) {
                // Respond with an error when the token is invalid
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }

            // Continue processing the request
            chain.doFilter(req, res);
        }
    }
}
