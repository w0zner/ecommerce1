package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.StockService;
import com.icodeap.ecommerce.application.service.ValidateStock;
import com.icodeap.ecommerce.domain.Product;
import com.icodeap.ecommerce.domain.Stock;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin/products/stock")
public class StockController {

    private final StockService stockService;

    private final ValidateStock validateStock;

    public StockController(StockService stockService, ValidateStock validateStock) {
        this.stockService = stockService;
        this.validateStock = validateStock;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Product producto = new Product();
        producto.setId(id);

        List<Stock> stocks = stockService.getStockByProduct(producto);
        model.addAttribute("stocks", stocks);
        model.addAttribute("idproduct", id);

        return "admin/stock/show";
    }

    @GetMapping("/create-unit-product/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("idproduct", id);
        return "admin/stock/create";
    }

    @PostMapping("/save-unit-product")
    public String save(Stock stock, @RequestParam(name = "idproduct") Integer idproduct) {
        Product product = new Product();
        product.setId(idproduct);
        stock.setProduct(product);
        stock.setDateUpdated(LocalDateTime.now());
        stock.setDateCreated(LocalDateTime.now());
        stock.setDescription("Inventario");
        stockService.saveStock(validateStock.calculateBalance(stock));

        return "redirect:/admin/products/show";
    }

}
