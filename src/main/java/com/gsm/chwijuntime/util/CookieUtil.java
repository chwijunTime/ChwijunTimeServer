package com.gsm.chwijuntime.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class CookieUtil {

    public Cookie createCookie(String cookieName, String value) {
        Cookie token = new Cookie(cookieName, value);
        token.setHttpOnly(true);
        token.setMaxAge(10000);
        token.setPath("/");
        return token;
    }

    public Cookie getCookie(HttpServletRequest req, String cookieName) {
        final Cookie[] cookies = req.getCookies();
        if(cookies == null) {
            return null;
        }
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookieName)){
                return cookie;
            }
        }
        return null;
    }
}
