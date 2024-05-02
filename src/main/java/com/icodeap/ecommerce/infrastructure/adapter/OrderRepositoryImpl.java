package com.icodeap.ecommerce.infrastructure.adapter;

import com.icodeap.ecommerce.application.repository.OrderRepository;
import com.icodeap.ecommerce.domain.Order;
import com.icodeap.ecommerce.domain.User;
import com.icodeap.ecommerce.infrastructure.mapper.OrderMapper;
import com.icodeap.ecommerce.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderCrudRepository repo;
    private final OrderMapper mapper;

    private final UserMapper userMapper;

    public OrderRepositoryImpl(OrderCrudRepository repo, OrderMapper mapper, UserMapper userMapper) {
        this.repo = repo;
        this.mapper = mapper;
        this.userMapper = userMapper;
    }

    @Override
    public Order createOrder(Order order) {
        return mapper.toOrder(repo.save(mapper.toOrderEntity(order)));
    }

    @Override
    public Iterable<Order> getOrders() {
        return mapper.toOrders(repo.findAll());
    }

    @Override
    public Iterable<Order> getOrdersByUser(User user) {
        return mapper.toOrders(repo.findByUser(userMapper.toUserEntity(user)));
    }
}
