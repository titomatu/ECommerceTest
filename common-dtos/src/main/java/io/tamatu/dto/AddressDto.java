package io.tamatu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {
    String address1;
    String address2;
    String city;
    String state;
    String zip;
    String email;
    String phone;
}
