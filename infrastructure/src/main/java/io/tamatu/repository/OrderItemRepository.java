package io.tamatu.repository;

import io.tamatu.data.OrderItem;
import io.tamatu.data.OrderItemPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPk> {
}
