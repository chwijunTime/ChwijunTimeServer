package com.gsm.chwijuntime.config;

import com.gsm.chwijuntime.advice.exception.CExpiredJwtException;
import com.gsm.chwijuntime.advice.exception.NotFoundBearer;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.util.CookieUtil;
import com.gsm.chwijuntime.util.JwtTokenProvider;
import com.gsm.chwijuntime.util.RedisUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.exception.ExtIOException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

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
    private final CustomUserDetailService customUserDetailService;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = httpServletRequest.getHeader("Authorization");
        String userEmail = null;

        try{
            if(jwtToken != null && jwtToken.startsWith("Bearer ")){
                jwtToken = jwtToken.substring(7);
                userEmail = jwtTokenProvider.getUserEmail(jwtToken);
            }

            if(userEmail != null){
                UserDetails userDetails = customUserDetailService.loadUserByUsername(userEmail);

                if(jwtTokenProvider.validateToken(jwtToken, userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (ExpiredJwtException e){   // 만약 유효기간을 넘겼다면??
            httpServletResponse.setHeader("message", e.getMessage());
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
