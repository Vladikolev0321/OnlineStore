package com.example.onlineStore.mappers;
import com.example.onlineStore.dto.AddressDto;
import com.example.onlineStore.entities.Address;
import com.example.onlineStore.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {
    @Mapping(target = "street", source = "dto.street")
    @Mapping(target = "city", source = "dto.city")
    @Mapping(target = "state", source = "dto.state")
    @Mapping(target = "zipCode", source = "dto.zipCode")
    @Mapping(target = "user.id", source = "dto.userId")
    Address convertDtoToEntity(AddressDto dto);
}
