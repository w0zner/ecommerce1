package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.ProductService;
import com.icodeap.ecommerce.domain.Product;
import com.icodeap.ecommerce.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/admin/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/create")
    public String create() {
        return "admin/products/create";
    }

    @PostMapping("/save-product")
    public String save(Product product, @RequestParam(name = "img") MultipartFile multipartFile, RedirectAttributes redirectAttrs, HttpSession session) throws IOException {
        log.info("Producto recibido: {}", product);
        productService.save(product, multipartFile, session);
        redirectAttrs.addFlashAttribute("save", "success");
        return "redirect:/admin/products/show";
    }

    @GetMapping("/show")
    public String showProduct(Model model, HttpSession session) {
        log.info("ID USER Obtenido desde la variable de sesion {}", Integer.parseInt(session.getAttribute("iduser").toString()));

        User user = new User();
        user.setId(Integer.parseInt(session.getAttribute("iduser").toString()));
        Iterable<Product> products = productService.getProductByUser(user);

        model.addAttribute("products", products);
        return "admin/products/show";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model) {
        Product product = productService.getProductById(id);
        log.info("Producto obtenido para editar{}", product);
        model.addAttribute("product", product);
        return "/admin/products/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id, RedirectAttributes redirectAttrs) {
        productService.delete(id);
        redirectAttrs.addFlashAttribute("delete", "success");
        return "redirect:/admin/products/show";
    }

}
