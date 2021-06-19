package com.gsm.chwijuntime.security;

import com.gsm.chwijuntime.advice.exception.NoTokenANDTokenWrongException;
import com.gsm.chwijuntime.security.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String JWTToken = jwtTokenProvider.resolveToken(httpServletRequest);

        try{
            if(JWTToken != null && jwtTokenProvider.validateToken(JWTToken)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(JWTToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (ExpiredJwtException e){   // 만약 유효기간을 넘겼다면??
            httpServletResponse.setHeader("message", e.getMessage());
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}