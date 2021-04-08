package com.gsm.chwijuntime.handler;

import org.springframework.security.access.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DuplicateContractingCompanyHandler {

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException,
            ServletException {
        response.sendRedirect("/exception/DuplicateContractingCompanyException");
    }

}
