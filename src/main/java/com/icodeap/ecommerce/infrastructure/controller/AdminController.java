package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.ProductService;
import com.icodeap.ecommerce.domain.Product;
import com.icodeap.ecommerce.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String home(Model model, HttpSession session) {
        log.info("ID USER Obtenido desde la variable de sesion {}", Integer.parseInt(session.getAttribute("iduser").toString()));
        User user = new User();
        user.setId(Integer.parseInt(session.getAttribute("iduser").toString()));
        Iterable<Product> products = productService.getProductByUser(user);

        model.addAttribute("products", products);
        return "admin/home";
    }

}
