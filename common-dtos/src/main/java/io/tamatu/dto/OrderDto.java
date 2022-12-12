package io.tamatu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private String orderId;
    private String customerId;
    private String subTotal;
    private Double totalAmt;
    private Double tax;
    private Double shippingCharges;
    private String title;
    private String shippingMode;
    private String orderStatus;

    private Double amount;
    private String paymentMode;

    private AddressDto billingAddress;
    private AddressDto shippingAddress;

    private List<OrderItemDto> orderItems;
}
