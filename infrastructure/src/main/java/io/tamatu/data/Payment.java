package io.tamatu.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ecommerce_payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @Column(name = "payment_id", nullable = false, unique = true)
    private String paymentId;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "payment_mode", nullable = false)
    private String paymentMode;

    @Column(name = "confirmation_number", nullable = false)
    private String confirmationNumber;

    @Column(name = "payment_status", nullable = false)
    private String paymentStatus;

    @Column(name = "createdAt", nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "payment")
    private Order order;
}
