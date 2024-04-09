package com.icodeap.ecommerce.application.service;

import com.icodeap.ecommerce.application.repository.OrderProductRepository;
import com.icodeap.ecommerce.domain.Order;
import com.icodeap.ecommerce.domain.OrderProduct;

import java.util.List;

public class OrderProductService {
    private final OrderProductRepository repo;

    public OrderProductService(OrderProductRepository repo) {
        this.repo = repo;
    }

    public OrderProduct create(OrderProduct orderProduct) {
        return repo.create(orderProduct);
    }

    public Iterable<OrderProduct> getOrderProducts() {
        return repo.getOrderProducts();
    }

    public List<OrderProduct> getOrdersProductByOrder(Order order){
        return repo.getOrdersProductByOrder(order);
    }
}
