package com.example.onlineStore.mappers;
import com.example.onlineStore.dto.CategoryDto;
import com.example.onlineStore.dto.OrderDto;
import com.example.onlineStore.entities.Category;
import com.example.onlineStore.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    @Mapping(target = "name", source = "dto.name")
    Category convertDtoToEntity(CategoryDto dto);
}