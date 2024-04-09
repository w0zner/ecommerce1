package com.icodeap.ecommerce.infrastructure.mapper;

import com.icodeap.ecommerce.domain.Order;
import com.icodeap.ecommerce.infrastructure.entity.OrderEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses={UserMapper.class})
public interface OrderMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "dateCreated", target = "dateCreated"),
            @Mapping(source = "user", target = "user")
    })
    Order toOrder(OrderEntity orderEntity);

    Iterable<Order> toOrders(Iterable<OrderEntity> orderEntities);

    @InheritInverseConfiguration
    OrderEntity toOrderEntity(Order order);

}
