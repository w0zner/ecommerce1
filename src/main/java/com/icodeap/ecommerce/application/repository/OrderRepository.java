package com.icodeap.ecommerce.application.repository;

import com.icodeap.ecommerce.domain.Order;
import com.icodeap.ecommerce.domain.User;

public interface OrderRepository {
    Order createOrder(Order order);
    Iterable<Order> getOrders();
    Iterable<Order> getOrdersByUser(User user);
}
