package io.tamatu.ports.spi;

import io.tamatu.dto.OrderDto;

import java.util.Optional;

public interface OrderPersistencePort {

    public OrderDto updateOrderStatus(String orderId, String orderStatus);

    public Optional<OrderDto> findOrderById(String id);

    public OrderDto createOrder(OrderDto orderDto);
}
