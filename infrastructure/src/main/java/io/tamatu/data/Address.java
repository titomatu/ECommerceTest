package io.tamatu.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ecommerce_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @Column(name = "address_id", nullable = false, unique = true)
    private String addressId;

    @Column(name = "address1", nullable = false)
    private String address1;

    @Column(name = "address2", nullable = false)
    private String address2;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "zip", nullable = false)
    private String zip;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "billingAddress")
    private Order billedOrder;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "shippingAddress")
    private Order shippedOrder;

}
