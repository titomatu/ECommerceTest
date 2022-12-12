package io.tamatu.repository;

import io.tamatu.data.OrderItem;
import io.tamatu.data.OrderItemPk;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, OrderItemPk> {
}
