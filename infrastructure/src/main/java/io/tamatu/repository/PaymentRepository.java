package io.tamatu.repository;

import io.tamatu.data.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
