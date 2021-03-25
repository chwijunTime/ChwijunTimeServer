package com.gsm.chwijuntime.config;

import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.util.CookieUtil;
import com.gsm.chwijuntime.util.JwtTokenProvider;
import com.gsm.chwijuntime.util.RedisUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final CookieUtil cookieUtil;
    private final RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = httpServletRequest.getHeader("Authorization");

        String userEmail = null;
        String refreshJwt = null;
        String refreshUName = null;

        try{
            if(jwtToken != null){
                userEmail = jwtTokenProvider.getUserEmail(jwtToken);
            }
            if(userEmail != null){
                UserDetails userDetails = customUserDetailService.loadUserByUsername(userEmail);

                if(jwtTokenProvider.validateToken(jwtToken, userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }catch (ExpiredJwtException e){
            if(refreshJwt != null){
                refreshJwt = redisUtil.getData(refreshJwt);
            }
        }catch(Exception e){

        }

        //reFresh Token 발급하기
        try{
            if(refreshJwt != null){
                refreshUName = redisUtil.getData(refreshJwt);

                if(refreshUName.equals(jwtTokenProvider.getUserEmail(refreshJwt))){
                    UserDetails userDetails = customUserDetailService.loadUserByUsername(refreshUName);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                    Member member = new Member();
                    member.Change_Email(refreshUName);
                    String newToken = jwtTokenProvider.generateToken(member);
                    redisUtil.setDataExpire(newToken, member.getUsername(), jwtTokenProvider.REFRESH_TOKEN_VALIDATION_SECOND);
                }
            }
        }catch(ExpiredJwtException e){

        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
