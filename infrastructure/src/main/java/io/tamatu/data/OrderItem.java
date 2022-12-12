package io.tamatu.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ecommerce_order_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @EmbeddedId
    private OrderItemPk orderItemPk;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    @Column(name = "quantity", nullable = false)
    private String quantity;
}
