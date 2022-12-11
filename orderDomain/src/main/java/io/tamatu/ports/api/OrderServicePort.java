package io.tamatu.ports.api;

import io.tamatu.dto.OrderDto;

import java.util.Optional;

public interface OrderServicePort {

    public OrderDto updateOrderStatus(String orderId, String orderStatus);

    public Optional<OrderDto> findOrderById(String id);

    public OrderDto createOrder(OrderDto orderDto);
}
