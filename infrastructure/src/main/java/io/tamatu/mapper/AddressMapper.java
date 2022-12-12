package io.tamatu.mapper;

import io.tamatu.data.Address;
import io.tamatu.dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDto addressToAddressDto(Address address);

    Address addressDtoToAddress(AddressDto addressDto);

    List<AddressDto> addressListToAddressDtoList(List<Address> addressList);

    List<Address> addressDtoListToAddressList(List<AddressDto> addressDtoList);
}
