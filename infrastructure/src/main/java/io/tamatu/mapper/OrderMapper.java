package io.tamatu.mapper;

import io.tamatu.data.Order;
import io.tamatu.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto orderToOrderDto(Order order);

    Order orderDtoToOrder(OrderDto orderDto);

    List<OrderDto> orderListToOrderDtoList(List<Order> orderList);

    List<Order> orderDtoListToOrderList(List<OrderDto> orderDtoList);
}
