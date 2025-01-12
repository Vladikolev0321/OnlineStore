package com.example.onlineStore.mappers;

import com.example.onlineStore.dto.ReviewDto;
import com.example.onlineStore.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewMapper {
    @Mapping(target = "comment", source = "dto.comment")
    @Mapping(target = "rating", source = "dto.rating")
    Review convertDtoToEntity(ReviewDto dto);
}
