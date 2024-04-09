package com.icodeap.ecommerce.infrastructure.mapper;

import com.icodeap.ecommerce.domain.OrderProduct;
import com.icodeap.ecommerce.infrastructure.entity.OrderProductEntity;
import com.icodeap.ecommerce.infrastructure.entity.OrderProductPK;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, OrderMapper.class})
public interface OrderProductMapper {

    @Mappings({
            @Mapping(source = "pk.productEntity", target = "product"),
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(source = "pk.orderEntity", target = "order")
    })
    OrderProduct toOrderProduct(OrderProductEntity orderProductEntity);
    Iterable<OrderProduct> toOrderProducts(Iterable<OrderProductEntity> orderProductEntities);
    List<OrderProduct> toOrderProductList(Iterable<OrderProductEntity> orderProductEntities);


    @InheritInverseConfiguration
    OrderProductEntity toOrderProductEntity(OrderProduct orderProduct);
}
