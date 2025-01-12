@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    @Mapping(target = "name", source = "dto.name")
    Category convertDtoToEntity(CategoryDto dto);
}