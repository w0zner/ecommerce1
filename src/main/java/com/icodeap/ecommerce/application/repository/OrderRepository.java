package com.icodeap.ecommerce.application.repository;

import com.icodeap.ecommerce.domain.Order;

public interface OrderRepository {
    Order createOrder(Order order);
    Iterable<Order> getOrders();
}
