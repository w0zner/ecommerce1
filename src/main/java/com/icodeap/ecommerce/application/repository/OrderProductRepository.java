package com.icodeap.ecommerce.application.repository;

import com.icodeap.ecommerce.domain.Order;
import com.icodeap.ecommerce.domain.OrderProduct;

import java.util.List;

public interface OrderProductRepository {
    OrderProduct create(OrderProduct orderProduct);
    Iterable<OrderProduct> getOrderProducts();
    List<OrderProduct> getOrdersProductByOrder(Order order);
}
