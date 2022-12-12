package io.tamatu.controller;

import io.tamatu.dto.OrderCreateResponse;
import io.tamatu.dto.OrderDto;
import io.tamatu.ports.api.OrderServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderServicePort orderServicePort;

    @PostMapping("/orders")
    public ResponseEntity<OrderCreateResponse> createOrder(@RequestBody final OrderDto orderDto){
        OrderDto orderDto1 = this.orderServicePort.createOrder(orderDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new OrderCreateResponse(orderDto1.getOrderId(), orderDto1.getOrderStatus()));
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable final String orderId){
        OrderDto orderDto = this.orderServicePort.findOrderById(orderId);

        if(orderDto != null)
            return ResponseEntity.status(HttpStatus.FOUND).body(orderDto);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/orders/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable final String orderId, @RequestParam(name = "orderStatus") final String orderStatus){
        OrderDto orderDto = this.orderServicePort.findOrderById(orderId);

        if(orderDto != null){
            this.orderServicePort.updateOrderStatus(orderId, orderStatus);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
