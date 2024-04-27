package com.icodeap.ecommerce.application.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

public class LogoutService {

    public LogoutService() {
    }

    public void logout(HttpSession session) {
        if (session != null) {
            session.removeAttribute("iduser");
            session.invalidate();
        }
    }
}
