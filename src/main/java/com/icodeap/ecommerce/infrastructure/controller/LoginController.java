package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.LoginService;
import com.icodeap.ecommerce.infrastructure.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String login(){
        return "login";
    }

    @GetMapping("/access")
    public String acccess(UserDto userDto, HttpSession session) {
        userDto.setEmail(userDto.getUsername());
        log.info("EMAIL: {}", userDto.getEmail());
        log.info("PASS: {}", userDto.getPassword());
        //log.info("TYPE: {}", loginService.getUserType(userDto).name());

        if(loginService.existUser(userDto.getEmail())){
            session.setAttribute("iduser", loginService.getUserId(userDto.getEmail()));
            log.info("TYPE: {}", loginService.getUserType(userDto).name());

            if(loginService.getUserType(userDto).name().equals("ADMIN")){
                return "redirect:/admin";
            } else {
                return "redirect:/home";
            }
        }

        return "redirect:/home";
    }
}
