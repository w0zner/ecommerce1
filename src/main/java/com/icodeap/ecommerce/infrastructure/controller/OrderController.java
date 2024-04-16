package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.*;
import com.icodeap.ecommerce.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user/order")
@Slf4j
public class OrderController {
    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderProductService orderProductService;
    private final StockService stockService;
    private final ValidateStock validateStock;

    public OrderController(CartService cartService, UserService userService, ProductService productService, OrderService orderService, OrderProductService orderProductService, StockService stockService, ValidateStock validateStock) {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
        this.orderProductService = orderProductService;
        this.stockService = stockService;
        this.validateStock = validateStock;
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

    @GetMapping("/create-order")
    public String createOrder(RedirectAttributes redirectAttrs) {
        log.info("--Create Order--");
        //Obtener usuario temporal
        User user = userService.findById(1);

        //Save order
        Order order = new Order();
        order.setDateCreated(LocalDateTime.now());
        order.setUser(user);

        order = orderService.createOrder(order);

        //Save order product
        List<OrderProduct> orderProducts = new ArrayList<>();
        for(ItemCart item: cartService.getItemCarts()) {
            orderProducts.add(new OrderProduct(productService.getProductById(item.getIdProduct()), item.getQuantity(), order));
        }
        orderProducts.forEach(
                op-> {
                    orderProductService.create(op);
                    Stock stock = new Stock();
                    stock.setDateCreated(LocalDateTime.now());
                    stock.setProduct(op.getProduct());
                    stock.setDescription("Venta");
                    stock.setUnitIn(0);
                    stock.setUnitOut(op.getQuantity());
                    stockService.saveStock(validateStock.calculateBalance(stock));
                }

        );
        cartService.removeAllItemCart();
        redirectAttrs.addFlashAttribute("order_created", "ok");

        return "redirect:/home";
    }
}
