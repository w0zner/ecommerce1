package com.icodeap.ecommerce.infrastructure.adapter;

import com.icodeap.ecommerce.application.repository.OrderRepository;
import com.icodeap.ecommerce.domain.Order;
import com.icodeap.ecommerce.infrastructure.mapper.OrderMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderCrudRepository repo;
    private final OrderMapper mapper;

    public OrderRepositoryImpl(OrderCrudRepository repo, OrderMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public Order createOrder(Order order) {
        return mapper.toOrder(repo.save(mapper.toOrderEntity(order)));
    }

    @Override
    public Iterable<Order> getOrders() {
        return mapper.toOrders(repo.findAll());
    }
}
