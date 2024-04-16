package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
@RequestMapping("/user/cart")
@Slf4j
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-product")
    public String addProduct(@RequestParam Integer quantity, @RequestParam Integer idProduct, @RequestParam String nameProduct,@RequestParam BigDecimal price, RedirectAttributes redirectAttrs){
        cartService.addItemCart(quantity, idProduct, nameProduct, price);
        showCart();
        redirectAttrs.addFlashAttribute("added_product", "ok");
        return "redirect:/home/product-detail/"+idProduct;
    }

    @GetMapping("/get-cart")
    public String getCart(Model model){
        showCart();

        model.addAttribute("cart", cartService.getItemCarts());
        model.addAttribute("total", cartService.getTotalCart());

        return "user/cart/cart";
    }

    @GetMapping("/remove-item/{id}")
    public String removeItemCart(@PathVariable(name = "id") Integer idProduct){
        log.info("REMOVIENDO EL PRODUCTO {}", idProduct);
        cartService.removeItemCart(idProduct);
        return "redirect:/user/cart/get-cart";
    }

    private void showCart() {
        cartService.getItemCarts().forEach(
                itemCart -> log.info("Item cart: {}", itemCart)
        );
    }
}
