package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.ProductService;
import com.icodeap.ecommerce.application.service.StockService;
import com.icodeap.ecommerce.domain.Product;
import com.icodeap.ecommerce.domain.Stock;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final ProductService productService;

    private final StockService stockService;

    public HomeController(ProductService productService, StockService stockService) {
        this.productService = productService;
        this.stockService = stockService;
    }

    @GetMapping
    public String home(Model model, HttpSession session){
        if (session != null) {
            model.addAttribute("id", session.getAttribute("iduser"));
        }
        model.addAttribute("products", productService.getProducts());
        return "home";
    }

    @GetMapping("/product-detail/{id}")
    public String productDetail(@PathVariable Integer id, Model model, HttpSession session) {
        Integer lastBalance = 0;
        Product product = productService.getProductById(id);
        List<Stock> stocks = stockService.getStockByProduct(product);

        if(stocks.size() > 0){
            lastBalance = stocks.get(stocks.size()-1).getBalance();
        }

        model.addAttribute("product", product);
        model.addAttribute("stock", lastBalance);
        if (session != null) {
            model.addAttribute("id", session.getAttribute("iduser"));
        }

        return "user/productdetail.html";
    }

}
