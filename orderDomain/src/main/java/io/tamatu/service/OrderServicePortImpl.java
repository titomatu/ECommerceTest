package io.tamatu.service;

import io.tamatu.dto.OrderDto;
import io.tamatu.ports.api.OrderServicePort;
import io.tamatu.ports.spi.OrderPersistencePort;

public class OrderServicePortImpl implements OrderServicePort {

    private OrderPersistencePort orderPersistencePort;

    public OrderServicePortImpl(OrderPersistencePort orderPersistencePort){
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public OrderDto updateOrderStatus(String orderId, String orderStatus) {
        return null;
    }

    @Override
    public OrderDto findOrderById(String id) {
        return this.orderPersistencePort.findOrderById(id);
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        return null;
    }
}
