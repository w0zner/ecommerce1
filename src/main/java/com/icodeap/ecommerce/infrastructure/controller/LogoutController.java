package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/close")
@Slf4j
public class LogoutController {
    private final LogoutService logoutService;


    public LogoutController(LogoutService logoutService) {
        this.logoutService = logoutService;
    }

    @GetMapping
    public String logout(HttpSession session) {
        log.info("DATOS DE SESION: {}", session);
        logoutService.logout(session);
        return "redirect:/home";
    }
}
