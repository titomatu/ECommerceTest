package io.tamatu.adapter;

import io.tamatu.data.Order;
import io.tamatu.dto.OrderDto;
import io.tamatu.mapper.OrderMapper;
import io.tamatu.ports.spi.OrderPersistencePort;
import io.tamatu.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class OrderJpaAdapter implements OrderPersistencePort {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderDto updateOrderStatus(String orderId, String orderStatus) {
        return null;
    }

    @Override
    public OrderDto findOrderById(String id) {
        Optional<Order> order = this.orderRepository.findById(id);

        if(order.isPresent())
            return OrderMapper.INSTANCE.orderToOrderDto(order.get());

        return null;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        return null;
    }
}
