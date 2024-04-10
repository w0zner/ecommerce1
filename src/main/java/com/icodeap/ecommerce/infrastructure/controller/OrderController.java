package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.CartService;
import com.icodeap.ecommerce.application.service.UserService;
import com.icodeap.ecommerce.domain.ItemCart;
import com.icodeap.ecommerce.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user/order")
public class OrderController {
    private final CartService cartService;
    private final UserService userService;


    public OrderController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("/sumary-order")
    public String showSumaryOrder(Model model) {
        User user = userService.findById(1);

        List<ItemCart> items = cartService.getItemCarts();
        if(items.size() == 0){
            return "redirect:/user/cart/get-cart";
        }

        model.addAttribute("cart", cartService.getItemCarts());
        model.addAttribute("total", cartService.getTotalCart());
        model.addAttribute("user", user);

        return "user/sumaryorder";
    }
}
