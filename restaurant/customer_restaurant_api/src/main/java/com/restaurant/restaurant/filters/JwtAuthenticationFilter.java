package com.restaurant.restaurant.filters;

import com.restaurant.restaurant.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    private JwtUtils jwtUtils;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        super(authenticationManager);
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        Authentication authentication = getAuthentication(request);

        if(authentication != null){
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request,response);
    }

    private Authentication getAuthentication(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token == null){
            return null;
        }
        Claims claims = jwtUtils.getClaims(token.substring("Bearer ".length()));
        return new UsernamePasswordAuthenticationToken(claims, null);
    }
}
