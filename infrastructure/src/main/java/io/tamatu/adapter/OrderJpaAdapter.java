package io.tamatu.adapter;

import io.tamatu.data.*;
import io.tamatu.dto.OrderDto;
import io.tamatu.dto.OrderItemDto;
import io.tamatu.enums.OrderStatus;
import io.tamatu.enums.PaymentStatus;
import io.tamatu.mapper.AddressMapper;
import io.tamatu.mapper.OrderMapper;
import io.tamatu.ports.spi.OrderPersistencePort;
import io.tamatu.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class OrderJpaAdapter implements OrderPersistencePort {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private AddressRepository addressRepository;

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
            orderDto.setShippingAddress(AddressMapper.INSTANCE.addressToAddressDto(order1.getShippingAddress()));
            orderDto.setBillingAddress(AddressMapper.INSTANCE.addressToAddressDto(order1.getBillingAddress()));
            orderDto.setOrderItems(orderItemListToOrderItemDtoList(order1.getOrderItems()));


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

        Address shippingAddress = AddressMapper.INSTANCE.addressDtoToAddress(orderDto.getShippingAddress());
        shippingAddress.setAddressId(UUID.randomUUID().toString());

        Address billingAddress = AddressMapper.INSTANCE.addressDtoToAddress(orderDto.getBillingAddress());
        billingAddress.setAddressId(UUID.randomUUID().toString());

        Payment payment1 = this.paymentRepository.save(payment);
        Address shippingAddress1 = this.addressRepository.save(shippingAddress);
        Address billingAddress1 = this.addressRepository.save(billingAddress);

        order.setPayment(payment1);
        order.setShippingAddress(shippingAddress1);
        order.setBillingAddress(billingAddress1);

        Order order1 = this.orderRepository.save(order);
        if(orderDto.getOrderItems() != null){

            List<OrderItem> orderItemList = orderItemDtoListToOrderItemList(orderDto.getOrderItems(), order1.getOrderId());
            double total = 0;

            for (OrderItem p: orderItemList) {
                Optional<Product> productOptional = productRepository.findById(p.getOrderItemPk().getProductId());

                if(productOptional.isPresent()){
                    Product product = productOptional.get();
                    //2. Calculate price * qty
                    //3. summarize total
                    log.warn("========> QTY {} * VL {}",Double.valueOf(p.getQuantity()).doubleValue(), product.getPrice().doubleValue());
                    total += Double.valueOf(p.getQuantity()).doubleValue() * product.getPrice().doubleValue();
                } else {
                    total += 0.0; //Not necessary
                }
            }

            order1.setSubTotal(total);
            this.orderRepository.save(order1);

            this.orderItemRepository.saveAll(orderItemList);
        }

        return OrderMapper.INSTANCE.orderToOrderDto(order1);
    }

    private static List<OrderItem> orderItemDtoListToOrderItemList(List<OrderItemDto> orderItemDtoList, String orderId){

        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemDtoList.stream()
                .forEach(
                        o -> {
                            orderItemList.add(new OrderItem(
                                    new OrderItemPk(o.getProductId(), orderId),
                                    o.getQuantity()
                            ));
                        }
                );

        return orderItemList;
    }

    private static List<OrderItemDto> orderItemListToOrderItemDtoList(List<OrderItem> orderItemList){
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();

        orderItemList.stream()
                .forEach(
                        o -> {
                            orderItemDtoList.add(
                                    new OrderItemDto(
                                            o.getProduct().getProductId(),
                                            o.getQuantity()
                                    )
                            );
                        }
                );

        return orderItemDtoList;
    }

}
