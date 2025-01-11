package com.example.onlineStore.mappers;

import com.example.onlineStore.dto.CategoryDto;
import com.example.onlineStore.dto.OrderDto;
import com.example.onlineStore.entities.Category;
import com.example.onlineStore.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "totalAmount", source = "dto.totalAmount")
    Order convertDtoToEntity(OrderDto dto);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "totalAmount", source = "entity.totalAmount")
    @Mapping(target = "userId", source = "entity.user.id")
    @Mapping(target = "paymentId", source = "entity.payment.id")
    OrderDto convertEntityToDto(Order entity);
}