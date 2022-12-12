package io.tamatu.adapter;

import io.tamatu.data.Order;
import io.tamatu.data.Payment;
import io.tamatu.dto.OrderDto;
import io.tamatu.enums.OrderStatus;
import io.tamatu.enums.PaymentStatus;
import io.tamatu.mapper.OrderMapper;
import io.tamatu.ports.spi.OrderPersistencePort;
import io.tamatu.repository.OrderItemRepository;
import io.tamatu.repository.OrderRepository;
import io.tamatu.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

@Slf4j
public class OrderJpaAdapter implements OrderPersistencePort {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public OrderDto updateOrderStatus(String orderId, String orderStatus) {
        return null;
    }

    @Override
    public OrderDto findOrderById(String id) {
        Optional<Order> order = this.orderRepository.findById(id);

        if(order.isPresent()){
            Order order1 = order.get();
            OrderDto orderDto = OrderMapper.INSTANCE.orderToOrderDto(order1);
            orderDto.setAmount(order1.getPayment().getAmount());
            orderDto.setPaymentMode(order1.getPayment().getPaymentMode());

            return orderDto;
        }

        return null;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = OrderMapper.INSTANCE.orderDtoToOrder(orderDto);
        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderStatus(OrderStatus.RECEIVED);
        log.warn("Order Id to create {}", order.getOrderId());

        Payment payment = Payment.builder()
                .paymentId(UUID.randomUUID().toString())
                .paymentMode(orderDto.getPaymentMode())
                .amount(orderDto.getAmount())
                .confirmationNumber(UUID.randomUUID().toString())
                .paymentStatus(PaymentStatus.PROCESSING.name())
                .build();

        Payment payment1 = this.paymentRepository.save(payment);

        order.setPayment(payment1);

        Order order1 = this.orderRepository.save(order);

        return OrderMapper.INSTANCE.orderToOrderDto(order1);
    }

}
